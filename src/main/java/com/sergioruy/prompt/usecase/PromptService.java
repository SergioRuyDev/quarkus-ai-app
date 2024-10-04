package com.sergioruy.prompt.usecase;

import com.sergioruy.prompt.usecase.adapters.Assistant;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PromptService {

    private final Assistant assistant;

    public PromptService(Assistant assistant) {
        this.assistant = assistant;
    }

    private static final int MAX_CHAR_LIMIT = 1000; // Define your character limit here
    private final List<String> conversationHistory = new ArrayList<>(); // Maintain the conversation history

    public String promptResponse(String message) {
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
