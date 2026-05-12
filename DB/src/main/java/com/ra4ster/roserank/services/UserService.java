package com.ra4ster.roserank.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ra4ster.roserank.model.user.User;
import com.ra4ster.roserank.repositories.UserRepository;

import tools.jackson.databind.JsonNode;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public Optional<User> getUserByClerkId(String clerkId) {
        return userRepository.findByClerkId(clerkId);
    }

    @Transactional(readOnly = true)
    public void syncClerkUser(JsonNode data) {
        String clerkId = data.path("id").asString();
        
        // Clerk email_addresses is an array; safeguard against empty arrays
        String email = "";
        JsonNode emails = data.path("email_addresses");
        if (emails.isArray() && !emails.isEmpty()) {
            email = emails.get(0).path("email_address").asString();
        }

        String firstName = data.path("first_name").asString("");
        String lastName = data.path("last_name").asString("");

        User user = userRepository.findByClerkId(clerkId).orElse(new User());
        
        user.setClerkId(clerkId);
        user.setEmail(email);
        user.setName((firstName + " " + lastName).trim());

        userRepository.save(user);
    }

    @Transactional
    public void deleteUserByClerkId(String clerkId) {
        userRepository.findByClerkId(clerkId).ifPresent(userRepository::delete);
    }
}