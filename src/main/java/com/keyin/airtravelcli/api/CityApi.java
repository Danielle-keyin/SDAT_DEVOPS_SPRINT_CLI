package com.keyin.airtravelcli.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.keyin.airtravelcli.model.Airport;
import com.keyin.airtravelcli.model.City;
import com.keyin.airtravelcli.util.JsonParser;

import java.util.List;

public class CityApi {
    private final ApiClient client;
    public CityApi(ApiClient client) { this.client = client; }

    public List<City> listCities() {
        String json = client.get("/cities");
        return JsonParser.fromJson(json, new TypeReference<List<City>>() {});
    }

    public List<Airport> airportsInCity(long cityId) {
        String json = client.get("/cities/" + cityId + "/airports");
        return JsonParser.fromJson(json, new TypeReference<List<Airport>>() {});
    }
}
