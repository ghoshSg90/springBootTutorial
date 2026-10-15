package com.ecom.app;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<User> getAllUsers(){
        return userService.fetchAllUsers();
    }

    @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("api/users")
    public String createUser(@RequestBody User user){
        userService.addUser(user);

        return "User added successfully";
    }

}
