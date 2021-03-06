package org.o7planning.springjdbc.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@ComponentScan({ "org.o7planning.springjdbc" })
@EnableTransactionManagement
// Load the Environment
@PropertySources({ @PropertySource("classpath:ds/datasource-cfg.properties") })
public class AppConfiguration {
	// The Environment class serves as the property holder
	// and stores all the properties loaded by the @PropertySource
	@Autowired
	private Environment env;

	// Spring Bean
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		// See: datasource-cfg.properties
		dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
		dataSource.setUrl(env.getProperty("ds.url"));
		dataSource.setUsername(env.getProperty("ds.username"));
		dataSource.setPassword(env.getProperty("ds.password"));

		// schema init
		Resource initSchema = new ClassPathResource("script/schema.sql");
		DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
		DatabasePopulatorUtils.execute(databasePopulator, dataSource);


		System.out.println("## getDatasource :" + dataSource);
		return dataSource;
	}
}
