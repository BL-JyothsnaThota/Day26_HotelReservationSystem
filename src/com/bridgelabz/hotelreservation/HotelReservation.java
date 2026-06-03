package com.bridgelabz.hotelreservation;

import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class HotelReservation {

    public ArrayList<Hotel> hotelList = new ArrayList<>();

    public void addHotel(String name,
                         int regWeekday, int regWeekend,
                         int rewWeekday, int rewWeekend,
                         int rating) {

        hotelList.add(new Hotel(name,
                regWeekday, regWeekend,
                rewWeekday, rewWeekend,
                rating));
    }
    public static void main(String[] args) {

        HotelReservation reservation = new HotelReservation();

        // ✅ Updated hotel data (Regular + Reward rates + Rating)
        reservation.addHotel("Lakewood", 110, 90, 80, 80, 3);
        reservation.addHotel("Bridgewood", 150, 50, 110, 50, 4);
        reservation.addHotel("Ridgewood", 220, 150, 100, 40, 5);

        // ✅ Input dates
        String[] dates = {"11Sep2020", "12Sep2020"};

        // ✅ Choose customer type
        CustomerType type = CustomerType.REGULAR;
        // CustomerType type = CustomerType.REWARD;

        // ✅ Call FINAL UC8 method
        String result = reservation.findCheapestBestRatedHotel(dates, type);

        // ✅ Output
        System.out.println("Best Hotel: " + result);
    }
    public String findCheapestBestRatedHotel(String[] dates, CustomerType type) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");

        int minCost = Integer.MAX_VALUE;
        Hotel bestHotel = null;

        for (Hotel hotel : hotelList) {

            int totalCost = 0;

            for (String dateStr : dates) {

                LocalDate date = LocalDate.parse(dateStr, formatter);
                DayOfWeek day = date.getDayOfWeek();

                boolean isWeekend = (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY);

                if (type == CustomerType.REGULAR) {
                    totalCost += isWeekend ? hotel.regularWeekendRate : hotel.regularWeekdayRate;
                } else {
                    totalCost += isWeekend ? hotel.rewardWeekendRate : hotel.rewardWeekdayRate;
                }
            }

            // 🔥 FINAL DECISION LOGIC
            if (totalCost < minCost) {
                minCost = totalCost;
                bestHotel = hotel;
            }
            else if (totalCost == minCost) {
                if (hotel.rating > bestHotel.rating) {
                    bestHotel = hotel;
                }
            }
        }

        return bestHotel.name + ", Rating: " + bestHotel.rating +
                ", Total Cost: $" + minCost;
    }
    public String findBestRatedHotel() {

        Hotel bestHotel = null;
        int maxRating = Integer.MIN_VALUE;

        for (Hotel hotel : hotelList) {

            if (hotel.rating > maxRating) {
                maxRating = hotel.rating;
                bestHotel = hotel;
            }
        }

        return bestHotel.name + " with Rating: " + bestHotel.rating;
    }
}