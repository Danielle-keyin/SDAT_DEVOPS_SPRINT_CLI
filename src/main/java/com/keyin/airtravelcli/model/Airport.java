package com.keyin.airtravelcli.model;

public class Airport {
    private Long id;
    private String name;
    private String code;

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCode() { return code; }

    @Override public String toString() { return code + " — " + name; }
}
