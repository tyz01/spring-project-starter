package org.example.spring.dto;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface PersonalInfo2 {
    String getFirstname();
    String lastname();
    LocalDate birthDate();
    @Value("#{target.firstname + ' ' + target.lastname}")
    String getFullName();
}
