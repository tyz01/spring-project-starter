package org.example.spring.http.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.dto.UserCreateEditDto;
import org.example.spring.validation.group.CreateAction;
import org.example.spring.validation.group.UpdateAction;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;

@RestController
@RequestMapping("/")
public class UserController {

    @PostMapping("/create")
    public void create(@Validated({Default.class, CreateAction.class}) UserCreateEditDto user,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("create failed");
        }
    }

    @PatchMapping("/update")
    public void update(@Validated({Default.class, UpdateAction.class}) UserCreateEditDto user,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("update failed");
        }
    }
}
