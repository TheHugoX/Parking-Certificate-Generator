package com.obsbs.database.service;

import com.obsbs.core.beans.DriverBean;
import com.obsbs.database.worker.DriverWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DriverServiceImpl
  implements DriverService
{
  @Autowired
  private DriverWorker driverWorker;

  @Override
  @Transactional
  public void create(DriverBean driver)
  {
    driverWorker.save(driver);
  }

  @Override
  @Transactional
  public List<DriverBean> getByPriority(int max)
  {
    return driverWorker.getByPriority(max);
  }
}