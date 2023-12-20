package org.example.spring.database.pool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("pool")
public class ConnectionPool {
    private final String userName;
    private final Integer poolSize;

    public ConnectionPool(@Value("${db.username}") String userName, @Value("${db.poolSize}") Integer poolSize) {
        this.userName = userName;
        this.poolSize = poolSize;
    }

    @PostConstruct
    private void init() {
        System.out.println("init connection pool");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("Property set");
    }
}
