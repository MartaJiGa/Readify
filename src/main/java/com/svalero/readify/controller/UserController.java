package com.svalero.readify.controller;

import com.svalero.readify.domain.User;
import com.svalero.readify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //region GET
    @GetMapping("/users")
    public List<User> findAll(@RequestParam(defaultValue = "")String name){
        if(!name.isEmpty()){
            return userService.findByName(name);
        }
        return userService.getUsers();
    }
    @GetMapping("/user/{userId}")
    public Optional<User> getBook(@PathVariable long userId) {
        Optional<User> user = userService.getUserById(userId);
        return user;
    }
    //endregion

    //region POST
    @PostMapping("/users")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }
    //endregion

    //region PUT
    @PutMapping("/user/{userId}")
    public void modifyUser(@RequestBody User user, @PathVariable long userId) {
        userService.modifyUser(user, userId);
    }
    //endregion

    //region DELETE
    @DeleteMapping("/user/{userId}")
    public void removeUser(@PathVariable long userId) {
        userService.removeUser(userId);
    }
    //endregion
}
