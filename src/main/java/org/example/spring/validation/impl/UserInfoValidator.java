package org.example.spring.validation.impl;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.repository.CompanyRepository;
import org.example.spring.dto.BaseDto;
import org.example.spring.dto.UserCreateEditDto;
import org.example.spring.validation.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
//@RequiredArgsConstructor
public class UserInfoValidator implements ConstraintValidator<UserInfo, BaseDto> {

   // private final CompanyRepository companyRepository;

    @Override
    public boolean isValid(BaseDto value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value.getFirstname()) || StringUtils.hasText(value.getLastname());
    }
}
