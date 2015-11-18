package com.obsbs.database.domain;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "DRIVER")
public class DriverJPA
{
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "TEACHER", nullable = false, updatable = false)
  private String teacher;

  @Column(name = "CLASS_NAME", nullable = false, updatable = false)
  private String className;

  @Column(name = "FIRST_NAME", nullable = false, updatable = false)
  private String firstName;

  @Column(name = "LAST_NAME", nullable = false, updatable = false)
  private String lastName;

  @Column(name = "ADDRESS", nullable = false, updatable = false)
  private String address;

  @Column(name = "PLZ", nullable = false, updatable = false)
  private String plz;

  @Column(name = "TOWN", nullable = false, updatable = false)
  private String town;

  @Column(name = "KM_TO_SCHOOL", nullable = false, updatable = false)
  private int kmToSchool;

  @Column(name = "HOURS_TO_SCHOOL", nullable = false, updatable = false)
  private int hoursToSchool;

  @Column(name = "KFZ", nullable = false, updatable = false)
  private String kfz;

  @ElementCollection
  @CollectionTable(name = "PASSENGERS")
  private Set<PassengerJPA> passengers;

  @Column(name = "PRIORITY", nullable = false, updatable = false)
  private int priority;

  protected DriverJPA()
  {
  }

  public DriverJPA(String teacher, String className, String firstName, String lastName, String address, String plz, String town, int kmToSchool,
                   int hoursToSchool, String kfz, Set<PassengerJPA> passengers, int priority)
  {
    this.teacher = teacher;
    this.className = className;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.plz = plz;
    this.town = town;
    this.kmToSchool = kmToSchool;
    this.hoursToSchool = hoursToSchool;
    this.kfz = kfz;
    this.passengers = passengers;
    this.priority = priority;
  }

  public Long getId()
  {
    return id;
  }

  public String getTeacher()
  {
    return teacher;
  }

  public String getClassName()
  {
    return className;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getAddress()
  {
    return address;
  }

  public String getPlz()
  {
    return plz;
  }

  public String getTown()
  {
    return town;
  }

  public int getKmToSchool()
  {
    return kmToSchool;
  }

  public int getHoursToSchool()
  {
    return hoursToSchool;
  }

  public String getKfz()
  {
    return kfz;
  }

  public int getPriority()
  {
    return priority;
  }

  public Set<PassengerJPA> getPassengers()
  {
    return passengers;
  }
}