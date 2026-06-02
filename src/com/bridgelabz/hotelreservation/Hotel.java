package com.bridgelabz.hotelreservation;

public class Hotel {

    public String name;
    public int weekdayRate;
    public int weekendRate;
    public int rating;   // NEW FIELD

    public Hotel(String name, int weekdayRate, int weekendRate, int rating) {
        this.name = name;
        this.weekdayRate = weekdayRate;
        this.weekendRate = weekendRate;
        this.rating = rating;
    }
}