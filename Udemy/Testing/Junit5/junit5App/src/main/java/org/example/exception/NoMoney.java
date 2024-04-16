package org.example.exception;

public class NoMoney extends RuntimeException {
  public NoMoney(String noBalance) {
    super(noBalance);
  }
}
