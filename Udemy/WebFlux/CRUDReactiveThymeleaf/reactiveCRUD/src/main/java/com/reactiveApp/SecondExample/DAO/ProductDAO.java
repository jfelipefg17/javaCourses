package com.reactiveApp.SecondExample.DAO;

import com.reactiveApp.SecondExample.documents.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface ProductDAO extends ReactiveMongoRepository<Product, String> {

}
