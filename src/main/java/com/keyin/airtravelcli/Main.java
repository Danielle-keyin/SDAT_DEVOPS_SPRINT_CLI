package com.keyin.airtravelcli;

import com.keyin.airtravelcli.api.*;
import com.keyin.airtravelcli.model.Aircraft;
import com.keyin.airtravelcli.model.Airport;
import com.keyin.airtravelcli.model.City;
import com.keyin.airtravelcli.util.ConsoleHelper;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    private static final String BASE_URL = "http://localhost:8080";
//comment to test PR
    public static void main(String[] args) {
        ApiClient http = new ApiClient(BASE_URL);
        CityApi cities = new CityApi(http);
        PassengerApi passengers = new PassengerApi(http);
        AircraftApi aircraft = new AircraftApi(http);

        while (true) {
            System.out.println("\n=== Air Travel CLI ===");
            System.out.println("1) List all cities");
            System.out.println("2) Airports in a city");
            System.out.println("3) Aircraft flown by a passenger");
            System.out.println("4) Airports used by a passenger");
            System.out.println("5) Airports used by an aircraft (origins/destinations)");
            System.out.println("0) Exit");
            String choice = ConsoleHelper.prompt("Choose: ");

            try {
                switch (choice) {
                    case "1" -> {
                        List<City> list = cities.listCities();
                        if (list.isEmpty()) System.out.println("(no cities)");
                        else list.forEach(c -> System.out.println(" - " + c));
                    }
                    case "2" -> {
                        long id = ConsoleHelper.promptLong("City id: ");
                        List<Airport> aps = cities.airportsInCity(id);
                        if (aps.isEmpty()) System.out.println("(no airports found)");
                        else aps.forEach(a -> System.out.println(" - " + a));
                    }
                    case "3" -> {
                        long id = ConsoleHelper.promptLong("Passenger id: ");
                        Set<Aircraft> acs = passengers.aircraftFlownBy(id);
                        if (acs.isEmpty()) System.out.println("(no aircraft found)");
                        else acs.forEach(a -> System.out.println(" - " + a));
                    }
                    case "4" -> {
                        long id = ConsoleHelper.promptLong("Passenger id: ");
                        Set<Airport> aps = passengers.airportsUsedBy(id);
                        if (aps.isEmpty()) System.out.println("(no airports found)");
                        else aps.forEach(a -> System.out.println(" - " + a));
                    }
                    case "5" -> {
                        long id = ConsoleHelper.promptLong("Aircraft id: ");
                        Map<String, List<Airport>> map = aircraft.airportsForAircraft(id);
                        System.out.println("Origins:");
                        map.getOrDefault("origins", List.of()).forEach(a -> System.out.println(" - " + a));
                        System.out.println("Destinations:");
                        map.getOrDefault("destinations", List.of()).forEach(a -> System.out.println(" - " + a));
                    }
                    case "0" -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Unknown choice.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            ConsoleHelper.pause();
        }
    }
}
