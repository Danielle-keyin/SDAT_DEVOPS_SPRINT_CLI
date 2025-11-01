package com.keyin.airtravelcli.util;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {

    @Test
    void parsesSimpleJson() {
        String json = "{\"name\":\"New York\"}";
        Map<?, ?> map = JsonParser.fromJson(json, Map.class);
        assertEquals("New York", map.get("name"));
    }

    @Test
    void handlesInvalidJson() {
        assertThrows(RuntimeException.class, () -> JsonParser.fromJson("bad json", Map.class));
    }
}