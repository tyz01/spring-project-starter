package org.example;

import org.example.spring.configuration.ApplicationConfiguration;
import org.example.spring.database.pool.ConnectionPool;
import org.example.spring.database.repository.CrudRepository;
import org.example.spring.service.CompanyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext()) {
            context.register(ApplicationConfiguration.class);

            context.getEnvironment().setActiveProfiles("web", "prod"); //setActiveProfiles or getActiveProfiles

            context.refresh(); // start life cycle bean

            var companyService = context.getBean("companyService", CompanyService.class);
            companyService.findById(1);
        }

    }
}