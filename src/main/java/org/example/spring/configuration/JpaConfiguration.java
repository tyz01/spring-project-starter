package org.example.spring.configuration;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.configuration.condition.JpaCondition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Conditional(JpaCondition.class)
@Configuration
@Slf4j
public class JpaConfiguration {

//    @Bean
//    @ConfigurationProperties(prefix = "db")
//    public DataBaseProperties dataBaseProperties() {
//        return new DataBaseProperties();
//    }
    @PostConstruct
    void init() {
        log.info("jpa configuration is enable");
    }
}
