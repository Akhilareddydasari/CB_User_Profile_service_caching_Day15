package com.example.User_profile_service.repository;

import com.example.User_profile_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

}
