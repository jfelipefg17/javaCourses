package com.springBootReactive.app.entity;

import com.sun.jdi.request.StepRequest;

public class User {

  private String name;
  private String lastName;

  public User(){

  }

  public User(String name, String lastName){
    this.name = name;
    this.lastName = lastName;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName(){
    return this.name;
  }

  public void setLastName(String lastName){
    this.lastName = lastName;
  }

  public String getLastName(){
    return this.lastName;
  }

  @Override
  public String toString() {
    return "User{" +
            "name='" + name + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }
  
}
