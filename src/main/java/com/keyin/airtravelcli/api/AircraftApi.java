package com.keyin.airtravelcli.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.keyin.airtravelcli.model.Airport;
import com.keyin.airtravelcli.util.JsonParser;

import java.util.List;
import java.util.Map;

public class AircraftApi {
    private final ApiClient client;
    public AircraftApi(ApiClient client) { this.client = client; }

    public Map<String, List<Airport>> airportsForAircraft(long aircraftId) {
        String json = client.get("/aircraft/" + aircraftId + "/airports");
        return JsonParser.fromJson(json, new TypeReference<Map<String, List<Airport>>>(){});
    }
}
