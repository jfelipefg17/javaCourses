package org.example.entities;

public class CoffeeMachine {

  private InventoryManager inventoryManager;
  private Transaction transaction;
  private Payment payment;
  private BeverageOption beverageOption;
  private UserInterface userInterface;

  public CoffeeMachine(InventoryManager inventoryManager, Transaction transaction, Payment payment, BeverageOption beverageOption, UserInterface userInterface) {
    this.inventoryManager = inventoryManager;
    this.transaction = transaction;
    this.payment = payment;
    this.beverageOption = beverageOption;
    this.userInterface = userInterface;
  }
  public InventoryManager getInventoryManager() {
    return inventoryManager;
  }

  public void setInventoryManager(InventoryManager inventoryManager) {
    this.inventoryManager = inventoryManager;
  }

  public Transaction getTransaction() {
    return transaction;
  }

  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  public BeverageOption getBeverageOption() {
    return beverageOption;
  }

  public void setBeverageOption(BeverageOption beverageOption) {
    this.beverageOption = beverageOption;
  }

  public UserInterface getUserInterface() {
    return userInterface;
  }

  public void setUserInterface(UserInterface userInterface) {
    this.userInterface = userInterface;
  }
}
