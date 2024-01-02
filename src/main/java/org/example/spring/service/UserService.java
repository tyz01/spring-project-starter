package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.querydsl.QPredicates;
import org.example.spring.database.repository.CompanyRepository;
import org.example.spring.database.repository.UserRepository;
import org.example.spring.dto.UserCreateEditDto;
import org.example.spring.dto.UserFilter;
import org.example.spring.entity.User;
import org.example.spring.utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

        public ApiResponse<List<User>> findAll() {
        var response = new ApiResponse<List<User>>();
        List<User> users = new ArrayList<>();
        if (!userRepository.findAll().isEmpty()) {
            users = userRepository.findAll();
            response = new ApiResponse<>(users, false);
        } else {
            response = new ApiResponse<>(users, true);
        }
        return response;
    }

//    @PostFilter("filterObject.role.name().eqals('ADMIN')") // return only 5 by statement
//    @PostFilter("@companyService.findById(filterObject.company.id().isPresent())")
//    public ApiResponse<List<UserCreateEditDto>> findAll(ApiResponse<List<UserCreateEditDto>> list) {
//        return list;
//    }

    @PreAuthorize("hasAnyAuthority('ADMIN') and hasAnyAuthority('MODERATOR')") // or , and
    public ApiResponse<User> findById(Long id) {
        var response = new ApiResponse<User>();
        var user = new User();
        if (userRepository.findById(id).isPresent()) {
            user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            response = new ApiResponse<>(user, true);
        } else {
            response = new ApiResponse<>(user, false);
        }
        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }
}
