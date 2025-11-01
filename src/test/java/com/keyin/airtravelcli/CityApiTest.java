package com.keyin.airtravelcli;

import com.keyin.airtravelcli.api.ApiClient;
import com.keyin.airtravelcli.api.CityApi;
import com.keyin.airtravelcli.model.Airport;
import com.keyin.airtravelcli.model.City;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CityApiTest {

    private HttpServer server;
    private String baseUrl;

    @BeforeEach
    void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(0), 0);
        server.createContext("/cities", ex -> {
            byte[] body = """
                    [
                      {"id":1,"name":"St. John's","state":"NL","population":110000},
                      {"id":2,"name":"Halifax","state":"NS","population":430000}
                    ]""".getBytes();
            ex.getResponseHeaders().add("Content-Type", "application/json");
            ex.sendResponseHeaders(200, body.length);
            try (OutputStream os = ex.getResponseBody()) { os.write(body); }
        });
        server.createContext("/cities/1/airports", ex -> {
            byte[] body = """
                    [
                      {"id":1,"name":"St. John's Intl","code":"YYT"},
                      {"id":2,"name":"Military Field","code":"MIL"}
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
    void listCities_parsesTwo() {
        CityApi api = new CityApi(new ApiClient(baseUrl));
        List<City> cities = api.listCities();
        assertEquals(2, cities.size());
        assertEquals("St. John's", cities.get(0).getName());
    }

    @Test
    void airportsInCity_parsesAirports() {
        CityApi api = new CityApi(new ApiClient(baseUrl));
        List<Airport> aps = api.airportsInCity(1L);
        assertEquals(2, aps.size());
        assertEquals("YYT", aps.get(0).getCode());
    }
}
