package br.com.claro.inexus.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(
    basePackages = "br.com.claro.inexus.persistence.amazon.repository", 
    entityManagerFactoryRef = "amazonEntityManager", 
    transactionManagerRef = "amazonTransactionManager"
)
public class AmazonJpaConfig {

	@Autowired
    private Environment env;
     
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean amazonEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        
        em.setDataSource(amazonDataSource());
        em.setPackagesToScan(new String[] { "br.com.claro.inexus.persistence.amazon" });
 
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);
 
        return em;
    }
 
    @Primary
    @Bean
    public DataSource amazonDataSource() {
  
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
        dataSource.setDriverClassName(env.getProperty("amazon.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("amazon.datasource.url"));
        dataSource.setUsername(env.getProperty("amazon.datasource.username"));
        dataSource.setPassword(env.getProperty("amazon.datasource.password"));
 
        return dataSource;
    }
 
    @Primary
    @Bean
    public PlatformTransactionManager amazonTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(amazonEntityManager().getObject());
        return transactionManager;
    }
    
    @Bean
    public JdbcTemplate amanzonJdbcTemplate() {
    	return new JdbcTemplate(amazonDataSource());
    }
}
