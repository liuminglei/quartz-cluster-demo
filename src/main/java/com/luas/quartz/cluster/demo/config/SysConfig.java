package com.luas.quartz.cluster.demo.config;

import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
public class SysConfig {

    private final DataSource dataSource;

    public SysConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer() {
        return (schedulerFactoryBean) -> {
            schedulerFactoryBean.setDataSource(dataSource);
            schedulerFactoryBean.setConfigLocation(new ClassPathResource("quartz.properties"));
        };
    }

}
