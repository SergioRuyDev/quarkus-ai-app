package com.sergioruy.prompt.usecase.adapters;

import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface Assistant {

    String chat(String message);
}
