package org.example;

import org.example.spring.configuration.ApplicationConfiguration;
import org.example.spring.database.pool.ConnectionPool;
import org.example.spring.database.repository.CrudRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext()) {
            context.register(ApplicationConfiguration.class);
            var connectionPool = context.getBean("pool", ConnectionPool.class);
            context.getEnvironment().setActiveProfiles("web", "prod"); //setActiveProfiles or SetActiveProfiles
            context.refresh(); // start life cycle bean
            System.out.println(connectionPool);
            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }

    }
}