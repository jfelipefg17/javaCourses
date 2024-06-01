package com.reactiveApp.SecondExample.controllers;

import com.reactiveApp.SecondExample.DAO.ProductDAO;
import com.reactiveApp.SecondExample.documents.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

@Controller()
public class ProductController {

  @Autowired
  private ProductDAO dao;

  @GetMapping("/listProducts")
  public String listProducts(Model model){

    Flux<Product> products = dao.findAll();
    model.addAttribute("products", products);
    model.addAttribute("title", "List Of Products");

    return "productsList";
  }
}
