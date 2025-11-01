package com.keyin.airtravelcli.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ApiClient {
    private final String baseUrl;
    private final HttpClient http;

    public ApiClient() {
        this("http://localhost:8080");
    }

    public ApiClient(String baseUrl) {
        String b = baseUrl == null ? "" : baseUrl.trim();
        if (b.endsWith("/")) {
            b = b.substring(0, b.length() - 1);
        }
        this.baseUrl = b;
        this.http = HttpClient.newHttpClient();
    }


    public String buildUrl(String path) {
        if (path == null || path.isBlank()) return baseUrl;
        String p = path.startsWith("/") ? path : "/" + path;
        return baseUrl + p;
    }


    public String get(String endpoint) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(buildUrl(endpoint)))
                    .GET()
                    .build();

            HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Request failed: " + e.getMessage(), e);
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
