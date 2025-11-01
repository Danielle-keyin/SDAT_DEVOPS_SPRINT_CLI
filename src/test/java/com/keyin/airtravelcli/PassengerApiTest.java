package com.keyin.airtravelcli;

import com.keyin.airtravelcli.api.ApiClient;
import com.keyin.airtravelcli.api.PassengerApi;
import com.keyin.airtravelcli.model.Aircraft;
import com.keyin.airtravelcli.model.Airport;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PassengerApiTest {

    private HttpServer server;
    private String baseUrl;

    @BeforeEach
    void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(0), 0);
        server.createContext("/passengers/1/aircraft", ex -> {
            byte[] body = """
                    [
                      {"id":1,"type":"A320","airlineName":"Air Canada"},
                      {"id":2,"type":"B737","airlineName":"WestJet"}
                    ]""".getBytes();
            ex.getResponseHeaders().add("Content-Type", "application/json");
            ex.sendResponseHeaders(200, body.length);
            try (OutputStream os = ex.getResponseBody()) { os.write(body); }
        });
        server.createContext("/passengers/1/airports", ex -> {
            byte[] body = """
                    [
                      {"id":1,"name":"St. John's Intl","code":"YYT"},
                      {"id":3,"name":"Halifax Stanfield","code":"YHZ"}
                    ]""".getBytes();
            ex.getResponseHeaders().add("Content-Type", "application/json");
            ex.sendResponseHeaders(200, body.length);
            try (OutputStream os = ex.getResponseBody()) { os.write(body); }
        });
        server.start();
        baseUrl = "http://localhost:" + server.getAddress().getPort();
    }

    @AfterEach
    void stopServer() { server.stop(0); }

    @Test
    void aircraftFlownBy_returnsSet() {
        PassengerApi api = new PassengerApi(new ApiClient(baseUrl));
        Set<Aircraft> ac = api.aircraftFlownBy(1);
        assertEquals(2, ac.size());
        assertTrue(ac.stream().anyMatch(a -> "A320".equals(a.getType())));
    }

    @Test
    void airportsUsedBy_returnsSet() {
        PassengerApi api = new PassengerApi(new ApiClient(baseUrl));
        Set<Airport> aps = api.airportsUsedBy(1);
        assertEquals(2, aps.size());
        assertTrue(aps.stream().anyMatch(a -> "YHZ".equals(a.getCode())));
    }
}
