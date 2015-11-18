package com.obsbs.database.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "PASSENGER")
public class PassengerJPA
  implements Serializable
{
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "FIRST_NAME", nullable = false, updatable = false)
  private String firstName;

  @Column(name = "LAST_NAME", nullable = false, updatable = false)
  private String lastName;

  protected PassengerJPA()
  {
  }

  public PassengerJPA(String firstName, String lastName)
  {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Long getId()
  {
    return id;
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
