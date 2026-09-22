package org.cubeville.CVPointsAPI.rest.handlers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.cubeville.CVPointsAPI.rest.RestServer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public abstract class BaseHandler implements HttpHandler {
    protected static final Gson GSON = new Gson();

    // POST & Private Key Check, true = pass
    protected boolean validateRequest(HttpExchange exchange) throws IOException {
        // Method check
        if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            exchange.sendResponseHeaders(405, -1);
            return false;
        }

        // API Key check
        String apiKey = exchange.getRequestHeaders().getFirst("X-API-Key");
        if (!RestServer.privateKey.equals(apiKey)) {
            exchange.sendResponseHeaders(401, -1);
            return false;
        }

        return true;
    }

    // Read JSON and store as proper Object/Map
    protected <T> T readJson(HttpExchange exchange, Type type) throws IOException {
        // "Try with resources" - Automatically closes reader
        try (Reader reader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8)) {
            return GSON.fromJson(reader, type);
        } catch (JsonSyntaxException e) {
            exchange.sendResponseHeaders(400, -1);
            return null;
        }
    }

    // Convert back to JSON & send
    protected void sendJson(HttpExchange exchange, Object body) throws IOException {
        String json = GSON.toJson(body);
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, bytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}