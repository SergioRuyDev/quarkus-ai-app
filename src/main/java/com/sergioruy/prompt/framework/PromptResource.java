package com.sergioruy.prompt.framework;

import com.sergioruy.prompt.usecase.PromptService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestQuery;

@Path("/prompt")
public class PromptResource {

    private final PromptService promptService;

    public PromptResource(PromptService promptService) {
        this.promptService = promptService;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String prompt(@RestQuery("id") String id, @RestQuery("message") String message) {
        if (id == null || id.isEmpty()) {
            id = "default-memory-id";
        }
        return promptService.promptResponse(id, message);
    }
}
