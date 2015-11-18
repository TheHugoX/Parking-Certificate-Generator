package com.obsbs.database.worker;

import com.obsbs.core.beans.DriverBean;
import com.obsbs.core.beans.PassengerBean;
import com.obsbs.database.domain.DriverJPA;
import com.obsbs.database.domain.PassengerJPA;
import com.obsbs.database.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DriverWorker
{
  @Autowired
  private DriverRepository driverRepository;

  public void save(DriverBean driver)
  {
    Set<PassengerJPA> passengers = new HashSet<PassengerJPA>();
    for(PassengerBean passengerBean : driver.getPassengers())
    {
      passengers.add(new PassengerJPA(passengerBean.getFirstName(), passengerBean.getLastName()));
    }
    driverRepository.save(new DriverJPA(driver.getTeacher(), driver.getClassName(), driver.getFirstName(), driver.getLastName(), driver.getAddress(),
                                        driver.getPlz(), driver.getTown(), driver.getKmToSchool(), driver.getHoursToSchool(), driver.getKfz(), passengers,
                                        driver.getPriority()));
  }

  public List<DriverBean> getByPriority(int max)
  {
    List<DriverJPA> driverJPAs = driverRepository.findByPriority();
    if(driverJPAs.size() > max)
    {
      driverJPAs = driverJPAs.subList(0, max);
    }

    List<DriverBean> drivers = new ArrayList<>();
    for(DriverJPA driverJPA : driverJPAs)
    {
      Set<PassengerBean> passengers = new HashSet<>();
      drivers.add(new DriverBean(driverJPA.getTeacher(), driverJPA.getClassName(), driverJPA.getFirstName(), driverJPA.getLastName(), driverJPA.getAddress(),
                                 driverJPA.getPlz(), driverJPA.getTown(), driverJPA.getKmToSchool(), driverJPA.getHoursToSchool(), driverJPA.getKfz(),
                                 passengers, driverJPA.getPriority()));
    }
    return drivers;
  }
}
