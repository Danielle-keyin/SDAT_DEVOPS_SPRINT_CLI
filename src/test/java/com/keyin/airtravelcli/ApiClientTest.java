package com.keyin.airtravelcli;

import com.keyin.airtravelcli.api.ApiClient;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.*;

class ApiClientTest {

    private HttpServer server;
    private String baseUrl;

    @BeforeEach
    void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(0), 0); // random free port
        server.createContext("/ping", exchange -> {
            byte[] body = "{\"ok\":true}".getBytes();
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, body.length);
            try (OutputStream os = exchange.getResponseBody()) { os.write(body); }
        });
        server.createContext("/boom", exchange -> {
            byte[] body = "fail".getBytes();
            exchange.sendResponseHeaders(500, body.length);
            try (OutputStream os = exchange.getResponseBody()) { os.write(body); }
        });
        server.start();
        baseUrl = "http://localhost:" + server.getAddress().getPort();
    }

    @AfterEach
    void stopServer() { server.stop(0); }

    @Test
    void getReturnsBodyFor200() {
        ApiClient client = new ApiClient(baseUrl);
        String json = client.get("/ping");
        assertTrue(json.contains("\"ok\":true"));
    }

    @Test
    void getThrowsOnNon2xx() {
        ApiClient client = new ApiClient(baseUrl);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> client.get("/boom"));
        assertTrue(ex.getMessage().contains("500"));
    }
}
