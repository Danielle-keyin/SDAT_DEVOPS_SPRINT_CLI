package com.keyin.airtravelcli.model;

public class Aircraft {
    private Long id;
    private String type;
    private String airlineName;

    public Long getId() { return id; }
    public String getType() { return type; }
    public String getAirlineName() { return airlineName; }

    @Override public String toString() {
        return "#" + id + " " + type + (airlineName != null ? " (" + airlineName + ")" : "");
    }
}
