package org.example.database.repository;

import org.example.bpp.Auditing;
import org.example.bpp.Transaction;
import org.example.database.pool.ConnectionPool;
import org.example.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CompanyRepository implements CrudRepository<Integer, Company> {
    private final ConnectionPool pool;
    private final List<ConnectionPool> connectionPools;
    private final Integer poolSize;

    public CompanyRepository(ConnectionPool pool,
                             List<ConnectionPool> connectionPools,
                             @Value("${db.poolSize}") Integer poolSize) {
        this.pool = pool;
        this.connectionPools = connectionPools;
        this.poolSize = poolSize;
    }

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
