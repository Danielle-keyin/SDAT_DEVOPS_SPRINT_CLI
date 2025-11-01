package com.keyin.airtravelcli.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.keyin.airtravelcli.model.Airport;
import com.keyin.airtravelcli.util.JsonParser;

import java.util.List;

public class AirportApi {
    private final ApiClient client;
    public AirportApi(ApiClient client) { this.client = client; }

    public List<Airport> listAirports() {
        String json = client.get("/airports");
        return JsonParser.fromJson(json, new TypeReference<List<Airport>>() {});
    }

    public Airport getAirport(long id) {
        String json = client.get("/airports/" + id);
        return JsonParser.fromJson(json, Airport.class);
    }
}