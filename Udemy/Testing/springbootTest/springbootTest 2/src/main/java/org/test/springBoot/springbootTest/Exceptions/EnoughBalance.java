package org.test.springBoot.springbootTest.Exceptions;

public class EnoughBalance extends RuntimeException{
  public EnoughBalance(String message) {
    super(message);
  }
}
