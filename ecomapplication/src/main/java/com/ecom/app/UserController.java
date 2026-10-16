package com.ecom.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers(){
        //return ResponseEntity.ok(userService.fetchAllUsers());
        return new ResponseEntity<>(userService.fetchAllUsers(),
                                        HttpStatus.OK);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
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
    public ResponseEntity<String> createUser(@RequestBody User user){
        userService.addUser(user);

        return ResponseEntity.ok("User Added Successfully");
    }

    @PutMapping("api/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                           @RequestBody User updatedUser){
        boolean updated = userService.updateUserById(id, updatedUser);
        if(updated)
            return ResponseEntity.ok("User Updated Successfully");
        return ResponseEntity.notFound().build();
    }

}
