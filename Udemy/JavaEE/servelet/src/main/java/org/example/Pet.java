package org.example;


import jakarta.persistence.Entity;

@Entity
public class Pet extends Animal {

  private String dadName;

  public Pet(String dadName) {
    this.dadName = dadName;
  }

  public Pet(String name, int age, String color, int weight, String dadName) {
    super(name, age, color, weight);
    this.dadName = dadName;
  }

  public Pet() {

  }

  public String getDadName() {
    return dadName;
  }

  public void setDadName(String dadName) {
    this.dadName = dadName;
  }


  @Override
  public String toString() {
    return "Pet{" +
            "dadName='" + dadName + '\'' +
            '}';
  }
}
