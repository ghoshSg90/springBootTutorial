package com.ecom.app.controller;

import com.ecom.app.dto.UserRequest;
import com.ecom.app.dto.UserResponse;
import com.ecom.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        //return ResponseEntity.ok(userService.fetchAllUsers());
        return new ResponseEntity<>(userService.fetchAllUsers(),
                                        HttpStatus.OK);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
//        User returnUser = userService.getUserById(id);
//        if(returnUser == null)
//            return ResponseEntity.notFound().build();
//
//        return ResponseEntity.ok(returnUser);

        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("api/users")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest){
        userService.addUser(userRequest);

        return ResponseEntity.ok("User Added Successfully");
    }

    @PutMapping("api/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                           @RequestBody UserRequest updatedUserRequest){
        boolean updated = userService.updateUserById(id, updatedUserRequest);
        if(updated)
            return ResponseEntity.ok("User Updated Successfully");
        return ResponseEntity.notFound().build();
    }

}
