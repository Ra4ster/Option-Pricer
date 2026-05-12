package com.ra4ster.roserank.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ra4ster.roserank.model.user.User;
import com.ra4ster.roserank.services.UserService;

@RestController
@RequestMapping("/api/webhooks/clerk")
public class ClerkWebhookController {

    private final UserService userService;

    public ClerkWebhookController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public ResponseEntity<String> testWebhookEndpoint() {
        return ResponseEntity.ok("Clerk webhook endpoint is reachable");
    }

    @PostMapping
    public ResponseEntity<String> handleWebhook(
            @RequestBody Map<String, Object> payload) {

        String eventType = (String) payload.get("type");

        Map<String, Object> data =
                (Map<String, Object>) payload.get("data");

        String clerkId = (String) data.get("id");

        if ("user.created".equals(eventType)) {

            User user = new User();

            user.setClerkId(clerkId);

            user.setEmail(
                ((java.util.List<Map<String, String>>) data.get("email_addresses"))
                    .get(0)
                    .get("email_address")
            );

            user.setName(
                data.get("first_name") + " " + data.get("last_name")
            );

            userService.save(user);
        } else if ("user.deleted".equals(eventType)) {
            userService.deleteByClerkId(clerkId);
        } else if ("user.updated".equals(eventType)) {

            userService.getUserByClerkId(clerkId).ifPresent(user -> {
                String email =
                    ((java.util.List<Map<String, String>>) data.get("email_addresses"))
                        .get(0)
                        .get("email_address");

                Object firstName = data.get("first_name");
                Object lastName = data.get("last_name");

                String name =
                    ((firstName != null ? firstName.toString() : "") + " " +
                     (lastName != null ? lastName.toString() : "")).trim();

                if (name.isBlank()) {
                    name = email;
                }

                user.setEmail(email);
                user.setName(name);

                userService.save(user);
            });
        }

        return ResponseEntity.ok("Webhook received");
    }
}