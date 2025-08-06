package com.example.User_profile_service.service;

import com.example.User_profile_service.entity.User;
import com.example.User_profile_service.repository.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Service
public class UserProfileService {

    private final UserRepository userRepository;
    private final Map<String, User> cache = new ConcurrentHashMap<>();

    public UserProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @CircuitBreaker(name = "userProfileService", fallbackMethod = "getUserProfileFromCache")
    public User getUserProfile(String username) {
        return userRepository.findById(username)
                .map(user -> {
                    cache.put(username, user); // Save to cache
                    return user;
                })
                .orElse(new User(username, "Unknown User", "No data in DB"));
    }

    public User getUserProfileFromCache(String username, Throwable t) {
        return cache.getOrDefault(username,
                new User(username, "Unknown User", "No data available. Using fallback."));
    }
}
