package com.ecom.app;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<User> userList = new ArrayList<>();
    private Long id = 0L;

    public List<User> fetchAllUsers(){
        return userList;
    }

    public void addUser(User user){
        user.setId(++id);
        userList.add(user);
    }

    public Optional<User> getUserById(Long id) {
        return userList.stream().
                filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public boolean updateUserById(Long id, User updatedUser){
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(existingUser -> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    return true;
                }).orElse(false);
    }
}
