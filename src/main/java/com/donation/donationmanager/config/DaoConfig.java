package com.donation.donationmanager.config;

import java.util.Properties;

import javax.sql.DataSource;
 
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
@Configuration
@EnableTransactionManagement
public class DaoConfig {
 
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.donation.donationmanager.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }
 
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/donationmanager");
        dataSource.setUsername("root");
        dataSource.setPassword("amy@cho27");
        return dataSource;
    }
 
    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
 
    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        //hibernateProperties.setProperty("hibernate.connection.storage_engine", "InnoDB");
        return hibernateProperties;
    }

}