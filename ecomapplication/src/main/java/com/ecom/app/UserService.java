package com.ecom.app;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

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

        //return userList;
    }

    public User getUserById(Long id) {
        for(User user : userList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return new User();
    }
}
