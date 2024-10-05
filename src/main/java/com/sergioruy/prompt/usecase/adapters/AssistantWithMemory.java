package com.sergioruy.prompt.usecase.adapters;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.SessionScoped;

@RegisterAiService
@SessionScoped
public interface AssistantWithMemory {

    @SystemMessage({
            "You are sports coach AI, named 'Coach Duncan'.",
            "Before providing information, always says 'I'm here to assist you.'"
    })
    String chat(@MemoryId String id, @UserMessage String message);
}
