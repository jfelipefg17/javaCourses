package com.reactiveApp.SecondExample.controllers;

import com.reactiveApp.SecondExample.DAO.ProductDAO;
import com.reactiveApp.SecondExample.documents.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
public class ProductController {

  @Autowired
  private ProductDAO dao;

  private static final Logger log = LoggerFactory.getLogger(ProductController.class);

  @GetMapping({"/listProducts", "/"})
  public String listProducts(Model model){

    Flux<Product> products = dao.findAll().map(product -> {

      product.setName(product.getName().toUpperCase());
      return product;
    });

    products.subscribe(product -> log.info(product.getName()));

    model.addAttribute("products", products);
    model.addAttribute("title", "List Of Products");
    return "productsList";
  }


  //manage back pressure with dataDriver
  @GetMapping("/listProductsDataDriver")
  public String listProductsDataDriver(Model model){

    Flux<Product> products = dao.findAll().map(product -> {
      product.setName(product.getName().toUpperCase());
      return product;
    }).delayElements(Duration.ofSeconds(1));

    products.subscribe(product -> log.info(product.getName()));

    model.addAttribute("products", new ReactiveDataDriverContextVariable(products, 1));
    model.addAttribute("title", "List Of Products");
    return "productsList";
  }

  //manage back pressure with ChunkedFull
  @GetMapping("/listProductsChunkedFull")
  public String listProductsChunkedFull(Model model){

    Flux<Product> products = dao.findAll().map(product -> {
      product.setName(product.getName().toUpperCase());
      return product;
    }).repeat(5000);


    model.addAttribute("products", products);
    model.addAttribute("title", "List Of Products");
    return "productsList";
  }
}
