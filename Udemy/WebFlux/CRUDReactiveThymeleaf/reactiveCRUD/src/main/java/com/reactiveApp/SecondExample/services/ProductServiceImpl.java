package com.reactiveApp.SecondExample.services;

import com.reactiveApp.SecondExample.DAO.ProductDAO;
import com.reactiveApp.SecondExample.documents.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductDAO dao;


  @Override
  public Flux<Product> findAll() {
    return dao.findAll();
  }

  @Override
  public Flux<Product> findAllWithNameUpperCase() {
    return dao.findAll().map(product -> {
      product.setName(product.getName().toUpperCase());
      return product;
    });

  }

  @Override
  public Flux<Product> findAllWithNameUpperCaseChunkedRepeat() {
    return findAllWithNameUpperCase().repeat(5000);
  }

  @Override
  public Mono<Product> findById(String id) {
    return dao.findById(id);
  }

  @Override
  public Mono<Product> save(Product product) {
    return dao.save(product);
  }

  @Override
  public Mono<Product> update(Product product) {
    return dao.save(product);
  }

  @Override
  public Mono<Void> delete(String id) {
    return dao.deleteById(id);
  }
}
