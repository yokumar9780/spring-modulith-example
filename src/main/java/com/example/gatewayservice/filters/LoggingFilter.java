package com.example.gatewayservice.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class LoggingFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {

    @Override
    public ServerResponse filter(ServerRequest request, HandlerFunction<ServerResponse> next) throws Exception {
        String requestId = UUID.randomUUID().toString();
        long startTime = System.currentTimeMillis();

        logRequestDetails(request, requestId);

        try {
            ServerResponse response = next.handle(request);
            logResponseDetails(response, requestId, System.currentTimeMillis() - startTime);
            return response;
        } catch (Exception e) {
            log.error("Error processing request with ID {}: {}", requestId, e.getMessage(), e);
            throw e;
        }
    }

    private void logRequestDetails(ServerRequest request, String requestId) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("\n=== Request Begin ===\n");
        logMessage.append("Request ID: ").append(requestId).append("\n");
        logMessage.append("URI: ").append(request.uri()).append("\n");
        logMessage.append("Method: ").append(request.method()).append("\n");
        logMessage.append("Headers: ").append(request.headers().asHttpHeaders()).append("\n");
        logMessage.append("Query Parameters: ").append(request.params()).append("\n");
        logMessage.append("=== Request End ===\n");
        log.info(logMessage.toString());
    }

    private void logResponseDetails(ServerResponse response, String requestId, long executionTime) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("\n=== Response Begin ===\n");
        logMessage.append("Request ID: ").append(requestId).append("\n");
        logMessage.append("Status Code: ").append(response.statusCode()).append("\n");
        logMessage.append("Execution Time: ").append(executionTime).append(" ms\n");
        logMessage.append("Headers: ").append(response.headers()).append("\n");
        logMessage.append("=== Response End ===\n");
        log.info(logMessage.toString());
    }
}