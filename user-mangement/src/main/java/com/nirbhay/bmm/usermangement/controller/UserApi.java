package com.nirbhay.bmm.usermangement.controller;

import com.nirbhay.bmm.model.usermanagement.AppUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@Api(value = "User Management API")
public interface UserApi {

    @ApiOperation(value = "List users", notes = "Lists all users with pagination")
    @GetMapping(value = "/api/v1/users")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful operation"),
                    @ApiResponse(code = 400, message = "Bad Request"),
            }
    )
    ResponseEntity<Page<AppUser>> listUsers(@PageableDefault Pageable pageable);
    @ApiOperation(value = "Save user", notes = "Creates a new user or updates an existing user")
    @PostMapping(value = "/api/v1/users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User created or updated successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 409, message = "User already exists")
    })
    ResponseEntity<AppUser> saveUser(@RequestBody AppUser appUser) throws Exception;
    @ApiOperation(value = "Fetch user by ID", notes = "Retrieves a specific user by its ID")
    @GetMapping(value = "/api/v1/users/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 404, message = "User not found")
    })
    ResponseEntity<AppUser> fetchUserById(@PathVariable("id") Integer id) throws Exception;
}
