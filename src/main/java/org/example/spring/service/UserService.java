package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.repository.CrudRepository;
import org.example.spring.database.repository.UserRepository;
import org.example.spring.entity.Company;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final CrudRepository<Long, Company> companyRepository ;

}
