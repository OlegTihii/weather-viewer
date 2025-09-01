package org.weather.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.weather.repository.LocationRepositoryImpl;

import javax.sql.DataSource;

@Configuration
@Import({LiquibaseConfig.class})
public class DataSourceTestConfig {

    @Bean
    @Profile("test")
    public DataSource h2DataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:weather_test;DB_CLOSE_DELAY=-1;MODE=PostgreSQL;DATABASE_TO_UPPER=false;CASE_INSENSITIVE_IDENTIFIERS=TRUE");
        ds.setUsername("sa");
        ds.setPassword("");
        return ds;
    }
}
