package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.repository.CrudRepository;
import org.example.spring.dto.CompanyReadDto;
import org.example.spring.entity.Company;
import org.example.spring.listener.entity.AccessType;
import org.example.spring.listener.entity.EntityEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CrudRepository<Long, Company> companyRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final UserService userService;


    public Optional<CompanyReadDto> findById(Long id) {
        return companyRepository.findById(id).
                map(entity -> {
                    // TODO 07.12.2023
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return new CompanyReadDto(entity.getId());
                });
    }
}
