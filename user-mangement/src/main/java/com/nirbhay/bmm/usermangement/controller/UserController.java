package com.nirbhay.bmm.usermangement.controller;


import com.nirbhay.bmm.model.usermanagement.AppUser;

import com.nirbhay.bmm.usermangement.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController implements UserApi {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Page<AppUser>> listUsers(Pageable pageable) {
        log.info("Received request to list users with pagination: {}", pageable);
        Page<AppUser> users = userService.fetchAllUsers(pageable);
        log.info("Retrieved {} users", users.getTotalElements());
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<AppUser> saveUser(AppUser appUser) throws Exception {
        log.info("Received request to save user: {}", appUser);
        AppUser savedUser = userService.saveUser(appUser);
        log.info("User saved successfully: {}", savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @Override
    public ResponseEntity<AppUser> fetchUserById(Integer id) throws Exception {
        log.info("Received request to fetch user by ID: {}", id);
        AppUser user = userService.fetchUserById(id);
        if (user == null) {
            log.warn("User not found for ID: {}", id);
            return ResponseEntity.notFound().build();
        }
        log.info("User found: {}", user);
        return ResponseEntity.ok(user);
    }

}
