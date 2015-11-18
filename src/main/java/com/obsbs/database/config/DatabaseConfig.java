package com.obsbs.database.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories("com.obsbs.database.repository")
@ComponentScan({"com.obsbs.database.service.impl", "com.obsbs.database.worker"})
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class DatabaseConfig
{
  @Autowired
  private Environment environment;

  private String getProperty(String key)
  {
    return environment.getProperty(key);
  }

  @Bean
  public JpaVendorAdapter jpaVendorAdapter()
  {
    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    adapter.setShowSql(Boolean.parseBoolean(getProperty("database.showsql")));
    adapter.setGenerateDdl(Boolean.parseBoolean(getProperty("database.generateDdl")));
    return adapter;
  }

  @Bean
  public DataSource dataSource()
  {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUrl(getProperty("database.url"));
    dataSource.setUsername(getProperty("database.username"));
    dataSource.setPassword(getProperty("database.password"));
    dataSource.setDriverClassName(getProperty("database.driverClassName"));
    return dataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory()
  {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource());
    entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
    entityManagerFactoryBean.setPackagesToScan("com.obsbs.database.domain");
    return entityManagerFactoryBean;
  }

  @Bean
  public PlatformTransactionManager transactionManager()
  {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return transactionManager;
  }
}