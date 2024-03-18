package org.example.entities;

public class BeverageOption {

  private String name;
  private int sugar;
  private int milk;

  public BeverageOption(String name, int sugar, int milk) {
    this.name = name;
    this.sugar = sugar;
    this.milk = milk;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSugar() {
    return sugar;
  }

  public void setSugar(int sugar) {
    this.sugar = sugar;
  }

  public int getMilk() {
    return milk;
  }

  public void setMilk(int milk) {
    this.milk = milk;
  }
}
