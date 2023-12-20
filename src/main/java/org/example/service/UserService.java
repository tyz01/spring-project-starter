package org.example.service;

import org.example.database.repository.CompanyRepository;
import org.example.database.repository.CrudRepository;
import org.example.database.repository.UserRepository;
import org.example.entity.Company;
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
