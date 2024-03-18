package org.example.entities;

public class Transaction {

  private double amount;

  public Transaction(double amount) {
    this.amount = amount;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public void processPayment(Payment payment, double amunt1) {

  }

  public void selectedBeverage(BeverageOption beverageOption, BeverageOption option){

  }
}
