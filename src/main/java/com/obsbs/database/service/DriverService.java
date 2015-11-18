package com.obsbs.database.service;

import com.obsbs.core.beans.DriverBean;

import java.util.List;

public interface DriverService
{
  void create(DriverBean driver);

  List<DriverBean> getByPriority(int max);
}
