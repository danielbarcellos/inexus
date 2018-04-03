package br.com.claro.inexus.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    basePackages = "br.com.claro.inexus.persistence.hitxp.repository", 
    entityManagerFactoryRef = "hitxpEntityManager", 
    transactionManagerRef = "hitxpTransactionManager"
)
public class HitxpJpaConfig {

	@Autowired
    private Environment env;
     
    @Bean
    public LocalContainerEntityManagerFactoryBean hitxpEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        
        em.setDataSource(hitxpDataSource());
        em.setPackagesToScan(new String[] { "br.com.claro.inexus.persistence.hitxp" });
 
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);
 
        return em;
    }

    @Bean
    public DataSource hitxpDataSource() {
  
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
        dataSource.setDriverClassName(env.getProperty("hitxp.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("hitxp.datasource.url"));
        dataSource.setUsername(env.getProperty("hitxp.datasource.username"));
        dataSource.setPassword(env.getProperty("hitxp.datasource.password"));
 
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hitxpTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(hitxpEntityManager().getObject());
        return transactionManager;
    }
    
    @Bean
    public JdbcTemplate hitxpJdbcTemplate() {
    	return new JdbcTemplate(hitxpDataSource());
    }
}
