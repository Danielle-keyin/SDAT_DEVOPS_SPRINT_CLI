package com.keyin.airtravelcli;

import com.keyin.airtravelcli.api.ApiClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ApiClientTest {

    @Test
    void buildsBaseUrlCorrectly() {
        ApiClient client1 = new ApiClient("http://localhost:8080");
        assertEquals("http://localhost:8080/cities", client1.buildUrl("/cities"));
        assertEquals("http://localhost:8080/cities", client1.buildUrl("cities"));

        ApiClient client2 = new ApiClient("http://localhost:8080/");
        assertEquals("http://localhost:8080/cities", client2.buildUrl("/cities"));
        assertEquals("http://localhost:8080/cities", client2.buildUrl("cities"));
    }

    @Test
    void defaultBaseUrlIsLocalhost() {
        ApiClient client = new ApiClient();
        assertEquals("http://localhost:8080", client.getBaseUrl());
    }

    @Test
    void buildUrlHandlesBlankPath() {
        ApiClient client = new ApiClient("http://localhost:8080");
        assertEquals("http://localhost:8080", client.buildUrl(""));
        assertEquals("http://localhost:8080", client.buildUrl(null));
    }
}
