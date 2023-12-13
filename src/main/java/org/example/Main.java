package org.example;

import org.example.database.pool.ConnectionPool;
import org.example.database.repository.CompanyRepository;
import org.example.database.repository.CrudRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("application.xml")) {
            var connectionPool = context.getBean("pool", ConnectionPool.class);
            System.out.println(connectionPool);
            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }

    }
}