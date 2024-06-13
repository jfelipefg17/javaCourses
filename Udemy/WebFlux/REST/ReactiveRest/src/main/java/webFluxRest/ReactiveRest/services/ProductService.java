package webFluxRest.ReactiveRest.services;

import com.reactiveApp.SecondExample.documents.Category;
import com.reactiveApp.SecondExample.documents.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

  public Flux<Product> findAll();

  public Flux<Product> findAllWithNameUpperCase();

  public Flux<Product> findAllWithNameUpperCaseChunkedRepeat();

  public Mono<Product> findById(String id);

  public Mono<Product> save(Product product);

  public Mono<Product> update(Product product);

  public Mono<Void> delete(String id);

  public Flux<Category> findAllCategory();

  public Mono<Category> findByIdCategory(String id);

  public Mono<Category> saveCategory(Category category);
}
