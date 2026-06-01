package com.bridgelabz.hotelreservation;

import java.util.ArrayList;

public class HotelReservation {

    public ArrayList<Hotel> hotelList = new ArrayList<>();

    public void addHotel(String name, int weekdayRate, int weekendRate) {
        hotelList.add(new Hotel(name, weekdayRate, weekendRate));
    }
    public static void main(String[] args) {

            System.out.println("Welcome to Hotel Reservation Program");

            HotelReservation reservation = new HotelReservation();

            reservation.addHotel("Lakewood", 110, 90);
            reservation.addHotel("Bridgewood", 150, 50);
            reservation.addHotel("Ridgewood", 220, 150);

    }
    public String findCheapestHotel(int numberOfDays) {

        int minCost = Integer.MAX_VALUE;
        String cheapestHotel = "";

        for (Hotel hotel : hotelList) {

            int totalCost = hotel.rateForRegular * numberOfDays;

            if (totalCost < minCost) {
                minCost = totalCost;
                cheapestHotel = hotel.name;
            }
        }

        return cheapestHotel + ", Total Rates: $" + minCost;
    }
}