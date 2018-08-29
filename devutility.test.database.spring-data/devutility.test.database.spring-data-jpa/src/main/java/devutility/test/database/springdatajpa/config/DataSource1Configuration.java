package devutility.test.database.springdatajpa.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
@PropertySource("classpath:db.properties")
@EnableJpaRepositories(basePackages = "devutility.test.database.springdatajpa.dao.mssql", entityManagerFactoryRef = "entityManagerFactory1", transactionManagerRef = "transactionManager1")
public class DataSource1Configuration {
	@Primary
	@Bean
	@ConfigurationProperties("db1.sqlserver")
	public DataSource dataSource1() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties("db1.sqlserver.jpa")
	public HashMap<String, Object> jpaPropertyMap1() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
		map.put("hibernate.show_sql", true);
		map.put("hibernate.format_sql", true);
		return new HashMap<String, Object>();
	}

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory1() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSource1());
		localContainerEntityManagerFactoryBean.setPackagesToScan(new String[] { "devutility.test.database.springdatajpa.dao.mssql.entity" });
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		localContainerEntityManagerFactoryBean.setJpaPropertyMap(jpaPropertyMap1());
		return localContainerEntityManagerFactoryBean;
	}

	@Bean
	public PlatformTransactionManager transactionManager1() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory1().getObject());
		return transactionManager;
	}

}