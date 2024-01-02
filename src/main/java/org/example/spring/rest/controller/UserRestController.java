package org.example.spring.rest.controller;

import lombok.RequiredArgsConstructor;
import org.example.spring.dto.UserCreateEditDto;
import org.example.spring.entity.User;
import org.example.spring.service.UserService;
import org.example.spring.utility.ApiResponse;
import org.example.spring.validation.group.CreateAction;
import org.example.spring.validation.group.UpdateAction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserRestController {

    private final UserService userService;

    @GetMapping()
    public ApiResponse<List<User>> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ApiResponse<User> findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Validated({Default.class, CreateAction.class}) @RequestBody UserCreateEditDto user,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("create failed");
        } else {
            System.out.println(user);
        }
    }

    @PutMapping("/update")
    public void update(@Validated({Default.class, UpdateAction.class}) @RequestBody UserCreateEditDto user,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("update failed");
        } else {
            System.out.println(user);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete")
    public void delete(@Validated({Default.class, UpdateAction.class}) @RequestBody UserCreateEditDto user,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("update failed");
        } else {
            System.out.println(user);
        }
    }
}
