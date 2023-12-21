package org.example.spring.database.pool;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("pool")
@Slf4j
@RequiredArgsConstructor
public class ConnectionPool {

    @Value("${db.username}")
    private final String userName;
    @Value("${db.pool.size}")
    private final Integer poolSize;

    @PostConstruct
    private void init() {
       log.info("init connection pool");
    }

    @PreDestroy
    private void destroy() {
        log.info("Property set");
    }
}
