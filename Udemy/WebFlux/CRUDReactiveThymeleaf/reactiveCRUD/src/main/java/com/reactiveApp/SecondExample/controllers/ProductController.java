package com.reactiveApp.SecondExample.controllers;


import com.reactiveApp.SecondExample.documents.Product;
import com.reactiveApp.SecondExample.services.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;

@Controller
public class ProductController {

  @Autowired
  private ProductService service;

  private static final Logger log = LoggerFactory.getLogger(ProductController.class);

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
  public Mono<String> saveProduct(@Valid Product product, BindingResult result, Model model) {

    if (result.hasErrors()) {
      model.addAttribute("tittle", "Error");
      model.addAttribute("button", "Save");
      return Mono.just("createProductForm");
    } else {

      if (product.getDate() == null) {
        product.setDate(new Date());
      }

      return service.save(product).doOnNext(product1 -> {
        log.info("Product saved : " + product.getName() + " ID : " + product.getId());
      }).thenReturn("redirect:/listProducts?success=product+save");
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

































