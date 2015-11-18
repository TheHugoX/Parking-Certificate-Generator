package com.obsbs.database.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  private static final Logger LOG = LoggerFactory.getLogger(DatabaseConfig.class);

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

    boolean showSql = Boolean.parseBoolean(getProperty("database.showsql"));
    boolean generateDdl = Boolean.parseBoolean(getProperty("database.generateDdl"));

    adapter.setShowSql(showSql);
    adapter.setGenerateDdl(generateDdl);

    LOG.debug("Settings showsql to '" + showSql + "'");
    LOG.debug("Settings generateDdl to '" + generateDdl + "'");

    return adapter;
  }

  @Bean
  public DataSource dataSource()
  {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    String url = getProperty("database.url");
    String driverClassName = getProperty("database.driverClassName");

    dataSource.setUrl(url);
    dataSource.setUsername(getProperty("database.username"));
    dataSource.setPassword(getProperty("database.password"));
    dataSource.setDriverClassName(driverClassName);

    LOG.debug("Connected to database '" + url + "'");
    LOG.debug("Using driver '" + driverClassName + "'");

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