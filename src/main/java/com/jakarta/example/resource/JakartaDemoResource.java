package com.jakarta.example.resource;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/api") // This defines the base URI for this resource
public class JakartaDemoResource {

    @GET
    @Path("/hello") // This extends the base URI to /api/hello
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        return "Hello from Jersey!";
    }

    @GET
    @Path("/hellojson") // This extends the base URI to /api/hellojson
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getMessageInJson() {
        return Map.of("message", "Hello from Jersey!");
    }

    @POST
    @Path("/postexample")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postMessage(JsonNode jsonData) {
        // Here we're using JsonNode to represent incoming JSON data.
        // You could use a POJO if you have a defined structure for your JSON.

        // Extract data from the JSON node if needed
        String message = jsonData.has("message") ? jsonData.get("message").asText() : "No message provided";

        // Prepare response
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("receivedMessage", message);
        responseMap.put("serverResponse", "Thank you for posting!");

        // Convert the response map to JSON string if needed, but here we're using Jackson's JsonNode
        // ObjectMapper objectMapper = new ObjectMapper();
        // String jsonResponse = objectMapper.writeValueAsString(responseMap);

        // Return the response
        return Response.ok(responseMap).build();
    }
}