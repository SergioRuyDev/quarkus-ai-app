package com.sergioruy;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.ArrayList;
import java.util.List;

@Path("/prompt")
public class PromptResource {

    @Inject
    Assistant assistant;

    private static final int MAX_CHAR_LIMIT = 1000; // Define your character limit here
    private final List<String> conversationHistory = new ArrayList<>(); // Maintain the conversation history

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String prompt(@RestQuery("message") String message) {
        // Add user's message to the conversation history
        conversationHistory.add("You: " + message);

        // Build the full prompt with context from the conversation history
        StringBuilder fullPrompt = new StringBuilder();
        for (String entry : conversationHistory) {
            fullPrompt.append(entry).append("\n");
        }
        fullPrompt.append("Bot:");

        // Get the response from the AI
        String response = assistant.chat(fullPrompt.toString());

        // Add bot's response to the conversation history
        conversationHistory.add("Bot: " + response);

        // Trim the response if it exceeds the character limit
        if (response.length() > MAX_CHAR_LIMIT) {
            response = response.substring(0, MAX_CHAR_LIMIT) + "...";
        }

        return response;
    }
}
