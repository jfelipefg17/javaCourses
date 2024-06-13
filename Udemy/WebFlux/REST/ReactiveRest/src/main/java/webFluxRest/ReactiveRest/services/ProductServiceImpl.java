package webFluxRest.ReactiveRest.services;

import com.reactiveApp.SecondExample.DAO.CategoryDAO;
import com.reactiveApp.SecondExample.DAO.ProductDAO;
import com.reactiveApp.SecondExample.documents.Category;
import com.reactiveApp.SecondExample.documents.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductDAO dao;

  @Autowired
  private CategoryDAO categoryDAO;


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

  @Override
  public Flux<Category> findAllCategory() {
    return categoryDAO.findAll();
  }

  @Override
  public Mono<Category> findByIdCategory(String id) {
    return categoryDAO.findById(id);
  }

  @Override
  public Mono<Category> saveCategory(Category category) {
    return categoryDAO.save(category);
  }
}
