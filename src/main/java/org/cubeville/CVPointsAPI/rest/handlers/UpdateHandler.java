package org.cubeville.CVPointsAPI.rest.handlers;

import com.google.common.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import org.cubeville.CVPointsAPI.dto.update.UpdateSubRequest;
import org.cubeville.CVPointsAPI.dto.update.UpdateSubResponse;
import org.cubeville.CVPointsAPI.rest.services.UpdateService;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;

public class UpdateHandler extends BaseHandler {

    // For Gson reading as Map<UUID(Str), UpdateSubRequest>
    private static final Type MAP_TYPE = new TypeToken<Map<String, UpdateSubRequest>>() {}.getType();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if (!validateRequest(exchange)) return;

        Map<String, UpdateSubRequest> reqObj = readJson(exchange, MAP_TYPE);

        if (reqObj == null) return;

        Map<String, UpdateSubResponse> resObj;

        try {
            resObj = UpdateService.processUpdate(reqObj);
        } catch (Exception e) {
            exchange.sendResponseHeaders(500, -1);
            return;
        }

        sendJson(exchange, resObj);
    }
}