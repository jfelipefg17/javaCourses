package org.example;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Animal {


@Id
  private String name;
  private int age;
  private String color;
  private int weight;



  public Animal() {
  }

  public Animal(String name, int age, String color, int weight) {
    this.name = name;
    this.age = age;
    this.color = color;
    this.weight = weight;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }


  @Override
  public String toString() {
    return "Animal{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", color='" + color + '\'' +
            ", weight=" + weight +
            '}';
  }
}
