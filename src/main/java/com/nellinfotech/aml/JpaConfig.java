package com.nellinfotech.aml;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
@Configuration
public class JpaConfig {
	@Bean(name = "mySqlDataSource")
    @Primary
    public DataSource mySqlDataSource() 
    {
        DataSourceBuilder datasource= DataSourceBuilder.create();
        datasource.url("jdbc:mysql://localhost:3306/aml?useUnicode=yes&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false");
        datasource.username("root");
        datasource.password("root");
        return datasource.build();
    }
	
//	@Bean
//	public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		//vendorAdapter.setDatabase(Database.MYSQL);
//		vendorAdapter.setDatabase(Database.MYSQL);
//		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//
//		factory.setJpaVendorAdapter(vendorAdapter);
//		factory.setPackagesToScan("com.nellinfotech.repository");
//		factory.setDataSource(dataSource);
//		factory.afterPropertiesSet();
//		return factory.getObject();
//	}
}
