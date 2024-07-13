package com.javaguide.spring_boot_restful_webservices.controller;

import com.javaguide.spring_boot_restful_webservices.dto.UserDto;
import com.javaguide.spring_boot_restful_webservices.entity.User;
import com.javaguide.spring_boot_restful_webservices.exception.ErrorDetail;
import com.javaguide.spring_boot_restful_webservices.exception.ResourceNotFoundException;
import com.javaguide.spring_boot_restful_webservices.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private UserService userService;   //dependency injection @AllArgsConstructor annt. kullanılarak da yapılabilir.

    @Operation(
            summary = "Create USER REST API",
            description = "Createv CRUD REST API for User to save in db"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    //build create User REST API
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    //build get user by id REST API
    //http://lcoalhost:8080/api/users/1
    @Operation(
            summary = "Get USER REST API",
            description = "Get user by id CRUD REST API is used to get a single user from the db"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    //Build get all users REST API
    //http://lcoalhost:8080/api/users/
    @Operation(
            summary = "Get all users  USER REST API",
            description = "Get all users CRUD REST API is used to get all the users from the db"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    //Build get all users REST API
    @Operation(
            summary = "Update users  USER REST API",
            description = "Update users CRUD REST API is used to update a particular users from the db"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                            @Valid @RequestBody UserDto user){
        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    //Build delete user REST API
    //DTO kullanımı delete için gerek yoktur çünkü jpa kullanılmadan gerçekleştirilen bir işlemdir.
    @Operation(
            summary = "Delete user  USER REST API",
            description = "Delete user CRUD REST API is used to delete a particular user in the db"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return  new ResponseEntity<>("User successfully deleted!",HttpStatus.OK);
    }

   /* @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleResourceFoundException(ResourceNotFoundException exception,
                                                                    WebRequest webRequest){
        ErrorDetail errorDetail = new ErrorDetail(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }
    */
}
