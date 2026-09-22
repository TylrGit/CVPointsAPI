package org.cubeville.CVPointsAPI.rest.handlers;

import com.sun.net.httpserver.HttpExchange;
import org.cubeville.CVPointsAPI.dto.status.StatusRequest;
import org.cubeville.CVPointsAPI.dto.status.StatusSubResponse;
import org.cubeville.CVPointsAPI.rest.services.StatusService;

import java.io.*;
import java.util.Map;

public class StatusHandler extends BaseHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if (!validateRequest(exchange)) return;

        StatusRequest reqObj = readJson(exchange, StatusRequest.class);
        if (reqObj == null) return;

        Map<String, StatusSubResponse> resObj;

        try {
            resObj = StatusService.processStatus(reqObj);
        } catch (Exception e) {
            exchange.sendResponseHeaders(500, -1);
            return;
        }

        sendJson(exchange, resObj);
    }
}