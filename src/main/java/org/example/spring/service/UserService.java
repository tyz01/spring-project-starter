package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.repository.CompanyRepository;
import org.example.spring.database.repository.UserRepository;
import org.example.spring.entity.User;
import org.example.spring.utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
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
}
