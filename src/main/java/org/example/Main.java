package org.example;

import org.example.spring.configuration.JpaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

//@EntityScan
@SpringBootApplication
@ConfigurationPropertiesScan
public class Main {
    public static void main(String[] args) {
       var context = SpringApplication.run(Main.class, args);
        System.out.println(context.getBeanDefinitionCount());
    }
}