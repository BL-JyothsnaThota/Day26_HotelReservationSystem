package com.bridgelabz.hotelreservation;

import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class HotelReservation {

    public ArrayList<Hotel> hotelList = new ArrayList<>();

    public void addHotel(String name, int weekdayRate, int weekendRate, int rating) {
        hotelList.add(new Hotel(name, weekdayRate, weekendRate, rating));
    }
    public static void main(String[] args) {

        HotelReservation reservation = new HotelReservation();

        reservation.addHotel("Lakewood", 110, 90, 3);
        reservation.addHotel("Bridgewood", 150, 50, 4);
        reservation.addHotel("Ridgewood", 220, 150, 5);
    }
    public String findCheapestHotel(String[] dates) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");

        int minCost = Integer.MAX_VALUE;
        List<String> cheapestHotels = new ArrayList<>();

        for (Hotel hotel : hotelList) {

            int totalCost = 0;

            for (String dateStr : dates) {

                LocalDate date = LocalDate.parse(dateStr, formatter);
                DayOfWeek day = date.getDayOfWeek();

                if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                    totalCost += hotel.weekendRate;
                } else {
                    totalCost += hotel.weekdayRate;
                }
            }

            if (totalCost < minCost) {
                minCost = totalCost;
                cheapestHotels.clear();
                cheapestHotels.add(hotel.name);
            } else if (totalCost == minCost) {
                cheapestHotels.add(hotel.name);
            }
        }

        return String.join(" and ", cheapestHotels) + " with Total Rates $" + minCost;
    }
}