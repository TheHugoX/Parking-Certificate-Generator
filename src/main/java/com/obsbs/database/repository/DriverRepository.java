package com.obsbs.database.repository;

import com.obsbs.database.domain.DriverJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository
  extends JpaRepository<DriverJPA, Long>
{
  @Query("SELECT driver FROM DriverJPA driver ORDER BY driver.priority")
  List<DriverJPA> findByPriority();
}
