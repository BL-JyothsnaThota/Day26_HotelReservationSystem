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

        reservation.addHotel("Lakewood", 110, 90, 80, 80, 3);
        reservation.addHotel("Bridgewood", 150, 50, 110, 50, 4);
        reservation.addHotel("Ridgewood", 220, 150, 100, 40, 5);

        String[] dates = {"11Sep2020", "12Sep2020"};

        try {
            String result = reservation.findCheapestBestRatedHotel(
                    dates, CustomerType.REGULAR);

            System.out.println(result);

        } catch (HotelReservationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public String findCheapestBestRatedHotel(String[] dates, CustomerType type)
            throws HotelReservationException {

        if (dates == null || dates.length == 0) {
            throw new HotelReservationException("Date input cannot be null or empty");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");

        int minCost = Integer.MAX_VALUE;
        Hotel bestHotel = null;

        try {

            for (Hotel hotel : hotelList) {

                int totalCost = 0;

                for (String dateStr : dates) {

                    if (dateStr == null || dateStr.isEmpty()) {
                        throw new HotelReservationException("Invalid date value");
                    }

                    LocalDate date = LocalDate.parse(dateStr, formatter);
                    DayOfWeek day = date.getDayOfWeek();

                    boolean isWeekend = (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY);

                    if (type == CustomerType.REGULAR) {
                        totalCost += isWeekend ? hotel.regularWeekendRate : hotel.regularWeekdayRate;
                    } else {
                        totalCost += isWeekend ? hotel.rewardWeekendRate : hotel.rewardWeekdayRate;
                    }
                }

                // same UC9 logic
                if (totalCost < minCost) {
                    minCost = totalCost;
                    bestHotel = hotel;
                } else if (totalCost == minCost) {
                    if (hotel.rating > bestHotel.rating) {
                        bestHotel = hotel;
                    }
                }
            }

        } catch (Exception e) {
            throw new HotelReservationException("Invalid date format");
        }

        return bestHotel.name + ", Rating: " + bestHotel.rating +
                ", Total Cost: $" + minCost;
    }    public String findBestRatedHotel() {

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