package test.java;

import com.bridgelabz.hotelreservation.CustomerType;
import com.bridgelabz.hotelreservation.Hotel;
import com.bridgelabz.hotelreservation.HotelReservation;
import com.bridgelabz.hotelreservation.HotelReservationException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HotelReservationTest {


    @Test
    void givenRegularCustomer_ShouldReturnBestHotel() throws HotelReservationException {

        HotelReservation reservation = new HotelReservation();

        reservation.addHotel("Lakewood", 110, 90, 80, 80, 3);
        reservation.addHotel("Bridgewood", 150, 50, 110, 50, 4);
        reservation.addHotel("Ridgewood", 220, 150, 100, 40, 5);

        String[] dates = {"11Sep2020", "12Sep2020"};

        String result = reservation.findCheapestBestRatedHotel(dates, CustomerType.REGULAR);

        assertEquals("Bridgewood, Rating: 4, Total Cost: $200", result);
    }
    @Test
    void givenRewardCustomer_ShouldReturnBestHotel() throws HotelReservationException {

        HotelReservation reservation = new HotelReservation();

        reservation.addHotel("Lakewood", 110, 90, 80, 80, 3);
        reservation.addHotel("Bridgewood", 150, 50, 110, 50, 4);
        reservation.addHotel("Ridgewood", 220, 150, 100, 40, 5);

        String[] dates = {"11Sep2020", "12Sep2020"};

        String result = reservation.findCheapestBestRatedHotel(dates, CustomerType.REWARD);

        assertEquals("Ridgewood, Rating: 5, Total Cost: $140", result);
    }
    @Test
    void givenInvalidDate_ShouldThrowException() {

        HotelReservation reservation = new HotelReservation();

        reservation.addHotel("Lakewood", 110, 90, 80, 80, 3);

        String[] dates = {"INVALID"};

        assertThrows(HotelReservationException.class, () -> {
            reservation.findCheapestBestRatedHotel(dates, CustomerType.REGULAR);
        });
    }
    @Test
    void givenNullDates_ShouldThrowException() {

        HotelReservation reservation = new HotelReservation();

        assertThrows(HotelReservationException.class, () -> {
            reservation.findCheapestBestRatedHotel(null, CustomerType.REGULAR);
        });
    }
    @Test
    void givenEmptyDates_ShouldThrowException() {

        HotelReservation reservation = new HotelReservation();

        String[] dates = {};

        assertThrows(HotelReservationException.class, () -> {
            reservation.findCheapestBestRatedHotel(dates, CustomerType.REGULAR);
        });
    }
    @Test
    void givenRewardCustomer_WhenUsingStreams_ShouldReturnBestHotel()
            throws HotelReservationException {

        HotelReservation reservation = new HotelReservation();

        reservation.addHotel("Lakewood", 110, 90, 80, 80, 3);
        reservation.addHotel("Bridgewood", 150, 50, 110, 50, 4);
        reservation.addHotel("Ridgewood", 220, 150, 100, 40, 5);

        String[] dates = {"11Sep2020", "12Sep2020"};

        String result = reservation.findCheapestBestRatedHotelStream(dates);

        assertEquals("Ridgewood, Rating: 5 and Total Rates: $140", result);
    }
}