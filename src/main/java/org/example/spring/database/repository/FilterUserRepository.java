package org.example.spring.database.repository;

import org.example.spring.dto.UserFilter;
import org.example.spring.entity.User;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter userFilter);

}
