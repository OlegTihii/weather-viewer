package org.weather.config;

import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class DataSourceConfig {

//    @Bean
//    //@Profile("dev")
//    public DataSource dataSource() {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName("org.postgresql.Driver");
//        ds.setUrl("jdbc:postgresql://localhost:5432/weather_db?characterEncoding=UTF-8&useUnicode=true");
//        ds.setUsername("postgres");
//        ds.setPassword("12345");
//        return ds;
//    }

    @Bean
    // @Profile("dev")
    public DataSource h2DataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:weather_test;DB_CLOSE_DELAY=-1;MODE=PostgreSQL;DATABASE_TO_UPPER=false;CASE_INSENSITIVE_IDENTIFIERS=TRUE");
        ds.setUsername("sa");
        ds.setPassword("");
        return ds;
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        log.info("WHERE IS LIQUI");
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:/db/changelog/changelog-master.xml");
        liquibase.setShouldRun(true);
        return liquibase;
    }
}