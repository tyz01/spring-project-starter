package org.example.spring.database.repository;

import org.example.spring.dto.PersonalInfo;
import org.example.spring.dto.UserFilter;
import org.example.spring.entity.Role;
import org.example.spring.entity.User;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter userFilter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Long companyId, Role role);

    void updateCompanyAndRole(List<User> users);

    void updateCompanyAndRoleNamed(List<User> users);
}
