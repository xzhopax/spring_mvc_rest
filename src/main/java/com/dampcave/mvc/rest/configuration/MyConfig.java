package com.dampcave.mvc.rest.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration // замена applicationContext.xml
@ComponentScan(basePackages = "com.dampcave.mvc.rest") // замена  <context:component-scan base-package="" />
@EnableWebMvc // замена <mvc:annotation-driven/>
@EnableTransactionManagement //<tx:annotation-driven transaction-manager="transactionManager" />
public class MyConfig {



    // вместо  <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource"
    // вместо sessionFactory, коннектон пул для связи с базой данных, использует уже готовый класс
    // из c3p0.ComboPooledDataSource, это jdbc подключение, хронит подключения и закрывает когда они не нужны
    @Bean
    public DataSource dataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/my_db?useSSL=false");
            dataSource.setUser("bestuser");
            dataSource.setPassword("bestuser");

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

     // вместо <bean id="sessionFactory" class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
     // нужен для получения сессий, с помощью которых и осуществляется подключение к базе данных
     // связь с ентити, чтобы связать таблицу с классами
    @Bean
    public LocalSessionFactoryBean sessionFactory(){

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.dampcave.mvc.rest.entity");

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");

        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    //открывает и закрывает транзакции автоматически
    //<bean id="transactionManager" class="org.springframework.orm.hibernate5.HibernateTransactionManager">
    @Bean
    public HibernateTransactionManager transactionManager(){

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }

}
