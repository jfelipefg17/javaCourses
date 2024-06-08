package com.reactiveApp.SecondExample;

import com.reactiveApp.SecondExample.DAO.ProductDAO;
import com.reactiveApp.SecondExample.documents.Category;
import com.reactiveApp.SecondExample.documents.Product;
import com.reactiveApp.SecondExample.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.util.Date;

@SpringBootApplication
public class SecondExampleApplication implements CommandLineRunner {

  @Autowired
  private ProductDAO dao;

  @Autowired
  private ReactiveMongoTemplate mongoTemplate;

  @Autowired
  private ProductService service;

  private static final Logger log = LoggerFactory.getLogger(SecondExampleApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(SecondExampleApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {


    //we must delete always because if we dont do it when we restart again will be save
    mongoTemplate.dropCollection(Product.class).subscribe();
    mongoTemplate.dropCollection(Category.class).subscribe();


    Category electronic = new Category("Electronic");
    Category sport = new Category("Sport");
    Category software = new Category("software");
    Category furniture = new Category("furniture");

    Flux.just(electronic, sport, software, furniture)
            .flatMap(category -> service.saveCategory(category))
            .doOnNext(category -> {
              log.info("Category Created: " + category.getName());
            }).thenMany(
                    Flux.just(new Product("Tv samsung", 456.76, electronic),
                            new Product("Iphone 15", 45.76, electronic),
                            new Product("huawei", 56.6, electronic),
                            new Product("Apple watch", 46.7, software),
                            new Product("fifa", 6.76, sport),
                            new Product("Tv sony", 46.06, electronic),
                            new Product("Bose speaker", 446.65, electronic),
                            new Product("furniture", 69.6, furniture)
                    )
                    .flatMap(product -> {
                      product.setDate(new Date());
                      return service.save(product);
                    }))
            .subscribe(product -> log.info("Insert: " + product.getName()));

    // with this we transform to a single object to save it one by one
  }


}
