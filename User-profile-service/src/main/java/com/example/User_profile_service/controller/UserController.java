package com.example.User_profile_service.controller;

import com.example.User_profile_service.entity.User;
import com.example.User_profile_service.service.UserProfileService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserProfileService userProfileService;

    public UserController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userProfileService.getUserProfile(username);
    }
}
