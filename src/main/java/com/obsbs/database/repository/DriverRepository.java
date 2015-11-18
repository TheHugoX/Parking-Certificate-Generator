package com.obsbs.database.repository;

import com.obsbs.database.domain.DriverJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository
  extends JpaRepository<DriverJPA, Long>
{
}
