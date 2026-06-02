package com.bridgelabz.hotelreservation;

public class Hotel {

    public String name;
    public int weekdayRate;
    public int weekendRate;
    public int rateForRegular;

    public Hotel(String name, int weekdayRate, int weekendRate) {
        this.name = name;
        this.weekdayRate = weekdayRate;
        this.weekendRate = weekendRate;
    }
}