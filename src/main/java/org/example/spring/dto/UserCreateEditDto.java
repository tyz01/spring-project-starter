package org.example.spring.dto;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.example.spring.entity.Company;
import org.example.spring.entity.Role;
import org.example.spring.entity.UserChat;
import org.example.spring.validation.UserInfo;
import org.example.spring.validation.group.CreateAction;
import org.hibernate.envers.NotAudited;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo(groups = CreateAction.class)
public class UserCreateEditDto {
    Long id;
    @Email
    String username;
    @NotBlank(groups = CreateAction.class)
    String rawPassword;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;
    @NotBlank
    @Size(min = 3, max = 64)
    String firstname;
    String lastname;
    Role role;
    Long companyId;
}
