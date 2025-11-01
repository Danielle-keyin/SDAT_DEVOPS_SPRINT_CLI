package com.keyin.airtravelcli.model;

public class City {
    private Long id;
    private String name;
    private String state;
    private Integer population;

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getState() { return state; }
    public Integer getPopulation() { return population; }

    @Override public String toString() {
        return "#" + id + " " + name + (state != null ? ", " + state : "");
    }
}
