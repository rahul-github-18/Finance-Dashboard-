package com.finance.finance_dashboard.controller;

import com.finance.finance_dashboard.entity.User;
import com.finance.finance_dashboard.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service=service;
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return service.createUser(user);
    }

    @GetMapping
    public List<User> getUsers(){
        return service.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return service.getUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody User user){
        return service.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }

    @PatchMapping("/{id}/status")
    public User changeStatus(@PathVariable Long id,@RequestParam boolean active){
        return service.changeStatus(id,active);
    }
}