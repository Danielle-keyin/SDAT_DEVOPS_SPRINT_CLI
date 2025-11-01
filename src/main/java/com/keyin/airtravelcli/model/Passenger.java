package com.keyin.airtravelcli.model;

public class Passenger {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhoneNumber() { return phoneNumber; }

    @Override
    public String toString() {
        return "#" + id + " " + firstName + " " + lastName +
                (phoneNumber != null && !phoneNumber.isEmpty() ? " (" + phoneNumber + ")" : "");
    }
}
