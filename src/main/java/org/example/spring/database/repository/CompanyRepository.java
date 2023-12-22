package org.example.spring.database.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.spring.bpp.Auditing;
import org.example.spring.bpp.Transaction;
import org.example.spring.database.pool.ConnectionPool;
import org.example.spring.entity.Company;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Slf4j
@Transaction
@Auditing
@Repository
@RequiredArgsConstructor
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CompanyRepository implements CrudRepository<Long, Company> {
    private final ConnectionPool pool;
    private final List<ConnectionPool> connectionPools;
    @Value("${db.pool.size}")
    private final Integer poolSize;

    @PostConstruct
    private void init() {
        log.warn("init company repository");
    }

    @Override
    public Optional<Company> findById(Long id) {
        log.info("find by id");
        return Optional.of(new Company(id, null, Collections.emptyMap()));
    }

    @Override
    public void delete(Company entity) {
       log.info("delete method");
    }
}
