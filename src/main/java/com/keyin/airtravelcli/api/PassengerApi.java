package com.keyin.airtravelcli.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.keyin.airtravelcli.model.Aircraft;
import com.keyin.airtravelcli.model.Airport;
import com.keyin.airtravelcli.util.JsonParser;

import java.util.List;
import java.util.Set;

public class PassengerApi {
    private final ApiClient client;
    public PassengerApi(ApiClient client) { this.client = client; }

    public Set<Aircraft> aircraftFlownBy(long passengerId) {
        String json = client.get("/passengers/" + passengerId + "/aircraft");
        return JsonParser.fromJson(json, new TypeReference<Set<Aircraft>>() {});
    }

    public Set<Airport> airportsUsedBy(long passengerId) {
        String json = client.get("/passengers/" + passengerId + "/airports");
        return JsonParser.fromJson(json, new TypeReference<Set<Airport>>() {});
    }
}
