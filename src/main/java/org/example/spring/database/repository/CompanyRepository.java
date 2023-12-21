package org.example.spring.database.repository;

import lombok.RequiredArgsConstructor;
import org.example.spring.bpp.Auditing;
import org.example.spring.bpp.Transaction;
import org.example.spring.database.pool.ConnectionPool;
import org.example.spring.entity.Company;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Transaction
@Auditing
@Repository
@RequiredArgsConstructor
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CompanyRepository implements CrudRepository<Integer, Company> {
    private final ConnectionPool pool;
    private final List<ConnectionPool> connectionPools;
    @Value("${db.poolSize}")
    private final Integer poolSize;

    @PostConstruct
    private void init() {
        System.out.println("init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("find by id");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("delete method");
    }
}
