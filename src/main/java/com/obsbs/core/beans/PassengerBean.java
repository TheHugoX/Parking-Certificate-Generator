package com.obsbs.core.beans;

public class PassengerBean
{
  private String firstName;
  private String lastName;

  public PassengerBean(String firstName, String lastName)
  {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }
}
