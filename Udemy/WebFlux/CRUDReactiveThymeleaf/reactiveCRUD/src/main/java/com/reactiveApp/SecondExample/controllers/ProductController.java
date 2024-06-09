package com.reactiveApp.SecondExample.controllers;


import com.reactiveApp.SecondExample.documents.Category;
import com.reactiveApp.SecondExample.documents.Product;
import com.reactiveApp.SecondExample.services.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.net.MalformedURLException;
import java.net.http.HttpClient;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;

@Controller
public class ProductController {

  @Value("${config.uploads.path}")
  private String uploadPath;

  @Autowired
  private ProductService service;

  private static final Logger log = LoggerFactory.getLogger(ProductController.class);

  @ModelAttribute("categories")
  public Flux<Category> categoryFlux() {
    return service.findAllCategory();
  }

  @GetMapping("/uploads/img/{nombreFoto:.+}")
  public Mono<ResponseEntity<Resource>> verFoto(@PathVariable String nombreFoto) throws MalformedURLException {
    Path ruta = Paths.get(uploadPath).resolve(nombreFoto).toAbsolutePath();
    Resource imagen = new UrlResource(ruta.toUri());

    if (!imagen.exists() || !imagen.isReadable()) {
      throw new RuntimeException("Error: no se puede cargar la imagen " + nombreFoto);
    }

    return Mono.just(
            ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imagen.getFilename() + "\"")
                    .body(imagen)
    );
  }



  @GetMapping("/watch/{id}")
  public Mono<String> watch(Model model, @PathVariable String id) {


    return service.findById(id)
            .doOnNext(product -> {
              model.addAttribute("product", product);
              model.addAttribute("title", "Product Watch");
            }).switchIfEmpty(Mono.just(new Product()))
            .flatMap(product -> {
              if (product.getId() == null){
                return Mono.error(new InterruptedException("Product Does Not Exist"));
              }
              return Mono.just(product);
            }).then(Mono.just("watch"))
            .onErrorResume(ex -> Mono.just("redirect:/listProducts?success=product+does+not+exist"));
  }

  @GetMapping({"/listProducts", "/"})
  public String listProducts(Model model) {

    Flux<Product> products = service.findAllWithNameUpperCase();

    products.subscribe(product -> log.info(product.getName()));

    model.addAttribute("products", products);
    model.addAttribute("title", "List Of Products");
    return "productsList";
  }


  //manage back pressure with dataDriver
  @GetMapping("/listProductsDataDriver")
  public String listProductsDataDriver(Model model) {

    Flux<Product> products = service.findAllWithNameUpperCase().delayElements(Duration.ofSeconds(1));

    products.subscribe(product -> log.info(product.getName()));

    model.addAttribute("products", new ReactiveDataDriverContextVariable(products, 1));
    model.addAttribute("title", "List Of Products");
    return "productsList";
  }

  //manage back pressure with ChunkedFull
  @GetMapping("/listProductsChunkedFull")
  public String listProductsChunkedFull(Model model) {

    Flux<Product> products = service.findAllWithNameUpperCase();


    model.addAttribute("products", products);
    model.addAttribute("title", "List Of Products");
    return "productsList";
  }

  @GetMapping("/createProduct")
  public Mono<String> createProduct(Model model) {
    model.addAttribute("product", new Product());
    model.addAttribute("title", "Product Form");
    return Mono.just("createProductForm");
  }

  @PostMapping("/createProduct")
  public Mono<String> saveProduct(@Valid Product product, BindingResult result, Model model, @RequestPart FilePart file) {

    if (result.hasErrors()) {
      model.addAttribute("tittle", "Error");
      model.addAttribute("button", "Save");
      return Mono.just("createProductForm");
    } else {
      Mono<Category> categoryMono = service.findByIdCategory(product.getCategory().getId());

      return categoryMono.flatMap(category -> {
        if (product.getDate() == null) {
          product.setDate(new Date());
        }

        if(!file.filename().isEmpty()){
          product.setPhoto(UUID.randomUUID().toString() + "-" + file.filename()
                  .replace(" ", "")
                  .replace(":", "")
                  .replace("\\","")
          );
        }
        product.setCategory(category);
        return service.save(product);
      }).doOnNext(product1 -> {
        log.info("Product saved : " + product1.getName() + " ID : " + product1.getId());
        log.info("Category saved : " + product1.getCategory().getName() + " ID : " + product1.getCategory().getId());
      })
              .flatMap(product1 -> {
                if (!file.filename().isEmpty()) {
                  return file.transferTo(new File(uploadPath + product1.getPhoto()));
                }
                return Mono.empty();
              })
              .thenReturn("redirect:/listProducts?success=product+save");
    }
  }

  @GetMapping("/editProduct/{id}")
  public Mono<String> editProduct(@PathVariable String id, Model model) {
    Mono<Product> productMono = service.findById(id).doOnNext(product -> {
      log.info("Product updated : " + product.getName() + " ID : " + product.getId());
    });

    model.addAttribute("title", "Edit Product");
    model.addAttribute("product", productMono);

    return Mono.just("createProductForm");
  }

  @GetMapping("/deleteProduct/{id}")
  public Mono<String> deleteProduct(@PathVariable String id) {
    return service.findById(id)
            .defaultIfEmpty(new Product())
            .flatMap(product -> {
              if (product.getId() == null) {
                return Mono.error(new InterruptedException("Product not found"));
              }
              return Mono.just(product);
            })
            .flatMap(product -> {
              log.info("Product deleted : " + product.getName() + " ID : " + product.getId());
              return service.delete(product.getId());
            })
            .then(Mono.just("redirect:/listProducts?success=product+deleted"))
            .onErrorResume(ex -> Mono.just("redirect:/listProducts?error=product+not+deleted"));
  }
}

































