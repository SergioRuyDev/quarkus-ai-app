package com.sergioruy.prompt.usecase;

import com.sergioruy.prompt.usecase.adapters.AssistantWithMemory;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PromptService {

    private final AssistantWithMemory assistantWithMemory;

    public PromptService(AssistantWithMemory assistantWithMemory) {
        this.assistantWithMemory = assistantWithMemory;
    }

    public String promptResponse(String memoryId, String message) {
        try {
            return assistantWithMemory.chat(memoryId, message);
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while processing your request.";
        }
    }

//    private static final int MAX_CHAR_LIMIT = 1000; // Define your character limit here
//    private final List<String> conversationHistory = new ArrayList<>(); // Maintain the conversation history
//
//    public String promptResponse(String message) {
//        // Add user's message to the conversation history
//        conversationHistory.add("You: " + message);
//
//        // Build the full prompt with context from the conversation history
//        StringBuilder fullPrompt = new StringBuilder();
//        for (String entry : conversationHistory) {
//            fullPrompt.append(entry).append("\n");
//        }
//        fullPrompt.append("Bot:");
//
//        // Get the response from the AI
//        String response = assistant.chat(fullPrompt.toString());
//
//        // Add bot's response to the conversation history
//        conversationHistory.add("Bot: " + response);
//
//        // Trim the response if it exceeds the character limit
//        if (response.length() > MAX_CHAR_LIMIT) {
//            response = response.substring(0, MAX_CHAR_LIMIT) + "...";
//        }
//
//        return response;
//    }
}
