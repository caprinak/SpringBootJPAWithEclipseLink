package com.sap.aish.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class Config  {

		@Value("${dbURL}")
		String dbURL;
		
		@Value("${dbUser}")
		String dbUser;
		
		@Value("${dbPassword}")
		String dbPassword;
		
		@Value("${dbDriver}")
		String dbDriver;
		
		
//		@Autowired
//		DataSource dataSource;
//		
		private static final Log log = LogFactory.getLog(Config.class);
		
/*		@Bean
		public DataSource dataSource(DataSourceProperties prop) {
			log.warn("######   "+dbURL+" "+dbUser+" "+dbPassword);
		
			DataSource ds= prop.initializeDataSourceBuilder().driverClassName("com.mysql.jdbc.Driver").url(dbURL).username(dbUser).password(dbPassword).build();
			System.out.println("####    "+ds);
			return ds;
			
		}
*/
		
		@Bean
		//@ConfigurationProperties(prefix="spring.secondDatasource")
		public DataSource dataSource() {
			DataSource ds=DataSourceBuilder.create().driverClassName(dbDriver).url(dbURL).username(dbUser).password(dbPassword).build();
			return ds;
		}

		
		
	
		@Bean
		public JpaVendorAdapter eclipseLink() {
			EclipseLinkJpaVendorAdapter adapter = new EclipseLinkJpaVendorAdapter();
			log.warn("####     Entity Manager Factory Interface:  "+adapter.getEntityManagerFactoryInterface());
			log.warn("####     Entity Manager  Interface:  "+adapter.getEntityManagerInterface());
			log.warn("####     JPA Dialect:  "+adapter.getJpaDialect());
			log.warn("####     Persistence Provider:  "+adapter.getPersistenceProvider());
			//log.warn("####     DataSource:  "+dataSource);
			
			
			return adapter;
		}

		@Bean(name = "entityManagerFactory")
		public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter adapter, DataSource dataSource) {
			LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
			factory.setPackagesToScan("com.sap.aish.model");
//			factory.setJpaVendorAdapter(adapter);
//			factory.setDataSource(ds);
			factory.setPersistenceUnitName("SpringBootJPAWithEclipseLink");
			factory.setJpaVendorAdapter(eclipseLink());
			factory.setDataSource(dataSource());
			log.warn("######   "+factory+"    "+factory.getPersistenceUnitName());
			return factory;
		}

		@Bean
		@Autowired
		public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
			log.warn("######  Entity Manager factory in Transaction Manager : "+emf);
			
			return new JpaTransactionManager(emf);
		}
	

}

