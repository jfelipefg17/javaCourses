package com.reactiveApp.SecondExample.controllers;

import com.reactiveApp.SecondExample.DAO.ProductDAO;
import com.reactiveApp.SecondExample.documents.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

  @Autowired
  private ProductDAO dao;

  private static final Logger log = LoggerFactory.getLogger(ProductController.class);
  @Autowired
  private ProductController productController;

  @GetMapping()
  public Flux<Product> index(){

    return dao.findAll().map(product -> {

      product.setName(product.getName().toUpperCase());
      return product;
    }).doOnNext(product -> log.info(product.getName()));

  }

  @GetMapping("/{id}")
  public Mono<Product> show(@PathVariable String id) {
    Mono<Product> productFlux = dao.findById(id);
    return productFlux;
  }
}
