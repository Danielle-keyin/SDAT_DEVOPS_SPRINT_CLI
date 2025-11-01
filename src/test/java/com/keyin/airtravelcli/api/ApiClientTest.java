package com.keyin.airtravelcli.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiClientTest {

    @Test
    void buildsBaseUrlCorrectly() {
        ApiClient client = new ApiClient("http://localhost:8080/");
        assertTrue(client.toString().contains("localhost"));
    }
}