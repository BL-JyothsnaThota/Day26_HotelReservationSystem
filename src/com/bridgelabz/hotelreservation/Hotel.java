package com.bridgelabz.hotelreservation;

public class Hotel {

    String name;

    int regularWeekdayRate;
    int regularWeekendRate;

    int rewardWeekdayRate;
    int rewardWeekendRate;

    int rating;

    public Hotel(String name, int regularWeekdayRate, int regularWeekendRate,
                 int rewardWeekdayRate, int rewardWeekendRate, int rating) {

        this.name = name;
        this.regularWeekdayRate = regularWeekdayRate;
        this.regularWeekendRate = regularWeekendRate;
        this.rewardWeekdayRate = rewardWeekdayRate;
        this.rewardWeekendRate = rewardWeekendRate;
        this.rating = rating;
    }
}