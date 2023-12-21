package org.example.spring.database.repository;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UserRepository {
    @Qualifier("pool2")
    private final ConnectionPool connectionPool;
}
