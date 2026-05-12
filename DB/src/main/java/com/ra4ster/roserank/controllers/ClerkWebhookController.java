package com.ra4ster.roserank.controllers;

import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ra4ster.roserank.services.UserService;
import com.svix.Webhook;
import com.svix.exceptions.WebhookVerificationException;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

@RestController
@RequestMapping("/api/webhooks")
public class ClerkWebhookController {

    private final UserService userService;
    private final JsonMapper objectMapper;

    @Value("${clerk.webhook.secret}")
    private String webhookSecret;

    public ClerkWebhookController(UserService userService, JsonMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/clerk")
    public ResponseEntity<Void> handleClerkWebhook(
            @RequestBody String payload,
            @RequestHeader("svix-id") String svixId,
            @RequestHeader("svix-timestamp") String svixTimestamp,
            @RequestHeader("svix-signature") String svixSignature) {

        Map<String, List<String>> headerMap = Map.of(
            "svix-id", List.of(svixId),
            "svix-timestamp", List.of(svixTimestamp),
            "svix-signature", List.of(svixSignature)
        );

        BiPredicate<String, String> filter = (_, _) -> true;
        HttpHeaders netHeaders = HttpHeaders.of(headerMap, filter);

        Webhook webhook = new Webhook(webhookSecret);
        try {
            webhook.verify(payload, netHeaders);
        } catch (WebhookVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            JsonNode node = objectMapper.readTree(payload);
            String eventType = node.path("type").asString();
            JsonNode data = node.path("data");

            if ("user.created".equals(eventType) || "user.updated".equals(eventType)) {
                userService.syncClerkUser(data);
            } else if ("user.deleted".equals(eventType)) {
                String clerkId = data.path("id").asString();
                userService.deleteUserByClerkId(clerkId);
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}