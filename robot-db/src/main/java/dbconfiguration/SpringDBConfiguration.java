package dbconfiguration;


import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Properties;

/**
 * The purpose of this class is to prepare and configure DB in Spring.
 */
@Configuration
@ComponentScan(basePackages = "repositories")
@PropertySource("classpath:database.properties")
@EnableJpaRepositories("dao")
public class SpringDBConfiguration {

    @Autowired
    Environment env;

    /**
     * Bean to create BasicDataSource.
     *
     * @return - BasicDataSource object
     */
    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("driver"));
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setPassword(env.getProperty("password"));
        dataSource.setUsername(env.getProperty("database_user"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("show_sql", env.getProperty("show_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.default_schema", env.getProperty("hibernate.default_schema"));
        return properties;
    }

    /**
     * Entity manager factory bean.
     * @return - new factory bean of LocalContainerEntityManager
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.POSTGRESQL);
        entityManagerFactory.setJpaVendorAdapter(adapter);
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("domain");
        entityManagerFactory.setJpaProperties(hibernateProperties());
        return entityManagerFactory;
    }

    /**
     * JPA transaction manager.
     * @return - JpaTransactionManager
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}