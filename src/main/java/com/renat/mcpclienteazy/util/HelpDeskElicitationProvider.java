package com.renat.mcpclienteazy.util;

import io.modelcontextprotocol.spec.McpSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.mcp.annotation.McpElicitation;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HelpDeskElicitationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelpDeskElicitationProvider.class);


    @McpElicitation(clients = "mcpserverremoteeazy")
    public McpSchema.ElicitResult handleElicitationRequest(McpSchema.ElicitRequest request) {
        LOGGER.info("Received MCP elicitation request form server: {}", request.message());

        // Simulate a human filing in the requested form. The keys here match the
        // field names of the server's requested schema (TicketContactInfoL priority, contactPhone)
        Map<String, Object> userResponse = Map.of(
                "priority", "HIGH",
                "contactPhone", "+1-202-333-0177");

        LOGGER.info("responding to elicitation with ACCEPT and data: {}", userResponse);
        return McpSchema.ElicitResult.builder(McpSchema.ElicitResult.Action.ACCEPT)
                .content(userResponse)
                .build();
    }


}
