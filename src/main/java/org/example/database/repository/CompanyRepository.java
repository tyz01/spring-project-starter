package org.example.database.repository;

import org.example.bfpp.Auditing;
import org.example.bfpp.InjectBean;
import org.example.bfpp.Transaction;
import org.example.database.pool.ConnectionPool;
import org.example.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company>{
    @Autowired
    @Qualifier("pool")
    private ConnectionPool pool;

    @Value("${db.poolSize}")
    private Integer poolSize;
    @PostConstruct
    private void init(){
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
