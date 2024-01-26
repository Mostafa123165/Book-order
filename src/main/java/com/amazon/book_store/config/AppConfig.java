package com.amazon.book_store.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@EnableWebMvc
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.amazon.book_store")
@PropertySource({"classpath:persistence-mysql.properties"})
public class AppConfig {
	
	@Autowired
	private Environment env ;
	
	 
	@Bean
	public DataSource dataSource(){
		ComboPooledDataSource dataSource = 
					new ComboPooledDataSource() ;
	 	
		// set JDBC properties 
		
		try {
			dataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		dataSource.setUser(env.getProperty("jdbc.user"));		
		dataSource.setPassword(env.getProperty("jdbc.password"));
		
		// set connection Pool properties 
		
		dataSource.setInitialPoolSize(getConnectionPoolPropertiesByName("connectionPool.initialPoolSize"));
		dataSource.setMinPoolSize(getConnectionPoolPropertiesByName("connectionPool.minPoolSize"));
		dataSource.setMaxPoolSize(getConnectionPoolPropertiesByName("connectionPool.maxPoolSize"));
		dataSource.setMaxIdleTime(getConnectionPoolPropertiesByName("connectionPool.maxIdelTime"));
		
		return dataSource ;
	}
	
	public int getConnectionPoolPropertiesByName(String propName) {
		
		String propVal = env.getProperty(propName); 
		int propIntVal = Integer.parseInt(propVal);
		
		return propIntVal ;
		
	}
	
	public Properties getHibernateProperties() {
		Properties prop = new Properties() ;
		
		prop.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		prop.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		
		return prop ;
	}
	
	
	@Bean 
	public LocalSessionFactoryBean sessionFactoryBean() {
		LocalSessionFactoryBean sessionFactoryBean =
					new LocalSessionFactoryBean();
		
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactoryBean.setHibernateProperties(getHibernateProperties());
		
		return sessionFactoryBean ;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManger =
					new HibernateTransactionManager();
		
		txManger.setSessionFactory(sessionFactory);
		
		return txManger; 
	}
	
	
}
