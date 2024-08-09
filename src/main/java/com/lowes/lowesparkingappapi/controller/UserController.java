package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.model.AppUser;
import com.lowes.lowesparkingappapi.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final AppUserService userService;

    @Autowired
    public UserController(AppUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<AppUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public AppUser createUser(@RequestBody AppUser user) {
        return userService.saveUser(user);
    }

    @PutMapping("/users/{id}")
    public AppUser updateUser(@PathVariable Long id, @RequestBody AppUser user) {
        user.setId(id);
        return userService.saveUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
