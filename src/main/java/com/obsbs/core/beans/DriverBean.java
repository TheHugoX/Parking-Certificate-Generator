package com.obsbs.core.beans;

import java.util.Set;

public class DriverBean
{
  private String teacher;
  private String className;
  private String firstName;
  private String lastName;
  private String address;
  private String plz;
  private String town;
  private int kmToSchool;
  private int hoursToSchool;
  private String kfz;
  private Set<PassengerBean> passengers;
  private int priority;

  public DriverBean(String teacher, String className, String firstName, String lastName, String address, String plz, String town, int kmToSchool,
                    int hoursToSchool, String kfz, Set<PassengerBean> passengers, int priority)
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

  public Set<PassengerBean> getPassengers()
  {
    return passengers;
  }

  public int getPriority()
  {
    return priority;
  }
}
