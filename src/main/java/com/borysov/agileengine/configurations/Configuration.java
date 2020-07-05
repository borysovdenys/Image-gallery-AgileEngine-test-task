package com.borysov.agileengine.configurations;


import com.borysov.agileengine.helpers.RequestHelper;
import com.borysov.agileengine.models.AuthResponse;
import com.borysov.agileengine.properties.DBProperties;
import lombok.extern.java.Log;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.net.URISyntaxException;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages = {"com.borysov.agileengine"})
@Log
@EnableScheduling
public class Configuration extends WebMvcConfigurerAdapter {

    @Autowired
    private DBProperties dbProperties;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dbProperties.getDriverClassName());
        dataSource.setUrl(dbProperties.getUrl());
        dataSource.setUsername(dbProperties.getUsername());
        dataSource.setPassword(dbProperties.getPassword());
        return dataSource;
    }

    @Bean
    public AuthResponse getToken() throws URISyntaxException {
        log.info("getToken");
        return RequestHelper.getAuth();
    }
}
