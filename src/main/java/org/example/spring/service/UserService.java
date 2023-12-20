package org.example.spring.service;

import org.example.spring.database.repository.CrudRepository;
import org.example.spring.database.repository.UserRepository;
import org.example.spring.entity.Company;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final CrudRepository<Integer, Company> companyRepository ;
    public UserService(UserRepository userRepository,
                       CrudRepository<Integer, Company> companyRepository) { //bean will search by name property of companyRepository
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }
}
