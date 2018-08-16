package com.bellakin.shop.models;

import com.bellakin.core.persistence.model.GenericModel;

public class Customer extends GenericModel {

  private String firstName;
  private String lastName;

  public Customer() {
  }

  public Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return String.format(
      "Customer[id=%s, firstName='%s', lastName='%s']",
      getId(), firstName, lastName);
  }
}