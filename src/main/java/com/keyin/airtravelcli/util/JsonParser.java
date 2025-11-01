package com.keyin.airtravelcli.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    private static final ObjectMapper MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static <T> T fromJson(String json, Class<T> type) {
        try { return MAPPER.readValue(json, type); }
        catch (Exception e) { throw new RuntimeException("JSON parse error: " + e.getMessage(), e); }
    }

    public static <T> T fromJson(String json, TypeReference<T> typeRef) {
        try { return MAPPER.readValue(json, typeRef); }
        catch (Exception e) { throw new RuntimeException("JSON parse error: " + e.getMessage(), e); }
    }
}
