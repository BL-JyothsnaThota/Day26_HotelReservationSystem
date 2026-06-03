package com.bridgelabz.hotelreservation;

import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class HotelReservation {

    public ArrayList<Hotel> hotelList = new ArrayList<>();
    private static final String DATE_REGEX = "^(0[1-9]|[12][0-9]|3[01])[A-Za-z]{3}\\d{4}$";
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
    private int calculateCost(Hotel hotel, String[] dates, CustomerType type)
            throws HotelReservationException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");

        int totalCost = 0;

        for (String dateStr : dates) {

            if (!dateStr.matches(DATE_REGEX)) {
                throw new HotelReservationException("Invalid date format: " + dateStr);
            }

            LocalDate date = LocalDate.parse(dateStr, formatter);
            DayOfWeek day = date.getDayOfWeek();

            boolean isWeekend = (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY);

            if (type == CustomerType.REWARD) {
                totalCost += isWeekend ? hotel.rewardWeekendRate : hotel.rewardWeekdayRate;
            } else {
                totalCost += isWeekend ? hotel.regularWeekendRate : hotel.regularWeekdayRate;
            }
        }

        return totalCost;
    }
    public String findCheapestBestRatedHotelStream(String[] dates)
            throws HotelReservationException {

        if (dates == null || dates.length == 0) {
            throw new HotelReservationException("Date input cannot be null or empty");
        }

        return hotelList.stream()

                .map(hotel -> {
                    try {
                        int cost = calculateCost(hotel, dates, CustomerType.REWARD);
                        return new Object[]{hotel, cost};
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })

                .sorted((a, b) -> {
                    int costCompare = Integer.compare((int) a[1], (int) b[1]);
                    if (costCompare == 0) {
                        return Integer.compare(
                                ((Hotel) b[0]).rating,
                                ((Hotel) a[0]).rating);
                    }
                    return costCompare;
                })

                .map(result -> {
                    Hotel h = (Hotel) result[0];
                    int cost = (int) result[1];
                    return h.name + ", Rating: " + h.rating +
                            " and Total Rates: $" + cost;
                })

                .findFirst()
                .orElseThrow(() ->
                        new HotelReservationException("No hotels available"));
    }
    public String findCheapestBestRatedHotelStreamRegular(String[] dates)
            throws HotelReservationException {

        if (dates == null || dates.length == 0) {
            throw new HotelReservationException("Date input cannot be null or empty");
        }

        return hotelList.stream()

                .map(hotel -> {
                    try {
                        int cost = calculateCost(hotel, dates, CustomerType.REGULAR);
                        return new Object[]{hotel, cost};
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })

                .sorted((a, b) -> {
                    int costCompare = Integer.compare((int) a[1], (int) b[1]);

                    if (costCompare == 0) {
                        return Integer.compare(
                                ((Hotel) b[0]).rating,
                                ((Hotel) a[0]).rating);
                    }

                    return costCompare;
                })

                .map(result -> {
                    Hotel h = (Hotel) result[0];
                    int cost = (int) result[1];

                    return h.name + ", Rating: " + h.rating +
                            " and Total Rates: $" + cost;
                })

                .findFirst()
                .orElseThrow(() ->
                        new HotelReservationException("No hotels available"));
    }

}