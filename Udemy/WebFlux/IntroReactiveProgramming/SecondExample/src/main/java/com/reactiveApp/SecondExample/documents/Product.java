package com.reactiveApp.SecondExample.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(collection = "products")
public class Product {

  @Id
  private String id;
  private String name;
  private Double price;
  private Date date;


  public Product() {
  }

  public Product(String name, Double price) {
    this.name = name;
    this.price = price;
  }

  public Product(String id, String name, Double price, Date date) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.date = date;
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
}
