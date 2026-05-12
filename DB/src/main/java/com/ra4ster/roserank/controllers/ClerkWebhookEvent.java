package com.ra4ster.roserank.controllers;

import java.util.Map;

import lombok.Data;

@Data
public class ClerkWebhookEvent
{
	private String type; // i.e. "user.created"
	private Map<String, Object> data; // Contains user details like email, name, etc.
}
