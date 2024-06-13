package webFluxRest.ReactiveRest.documents;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Document(collection = "products")
public class Product {

  @Id
  private String id;
  @NotEmpty
  private String name;
  @NotNull
  private Double price;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date date;
  @Valid
  private Category category;
  private String photo;


  public Product() {
  }

  public Product(String name, Double price, Category category) {
    this.name = name;
    this.price = price;
    this.category = category;
  }
  public Product(String id, String name, Double price, Date date) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.date = date;
  }

  public Product(String id, String name, Double price, Date date, Category category) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.date = date;
    this.category = category;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }
}
