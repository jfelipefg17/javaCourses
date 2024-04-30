package org.test.springBoot.springbootTest.Entities;

public class Bank {
  private  Long id;
  private String name;
  private int numberOfTransfers;

  public Bank() {
  }

  public Bank(Long id, String name, int numberOfTransfers) {
    this.id = id;
    this.name = name;
    this.numberOfTransfers = numberOfTransfers;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getNumberOfTransfers() {
    return numberOfTransfers;
  }

  public void setNumberOfTransfers(int numberOfTransfers) {
    this.numberOfTransfers = numberOfTransfers;
  }


}
