package com.ra4ster.roserank.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ra4ster.roserank.model.user.User;
import com.ra4ster.roserank.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public Optional<User> getUserByClerkId(String clerkId) {
        return userRepository.findByClerkId(clerkId);
    }
    
    public User save(User user) {
    	return userRepository.save(user);
    }

    @Transactional
    public void deleteByClerkId(String clerkId) {
        userRepository.findByClerkId(clerkId).ifPresent(userRepository::delete);
    }
}