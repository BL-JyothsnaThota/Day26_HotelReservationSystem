package test.java;

import com.bridgelabz.hotelreservation.Hotel;
import com.bridgelabz.hotelreservation.HotelReservation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HotelReservationTest {

    @Test
    void givenDateRange_ShouldReturnCheapestBestRatedHotel() {

        HotelReservation reservation = new HotelReservation();

        reservation.addHotel("Lakewood", 110, 90, 3);
        reservation.addHotel("Bridgewood", 150, 50, 4);
        reservation.addHotel("Ridgewood", 220, 150, 5);

        String[] dates = {"11Sep2020", "12Sep2020"};

        String result = reservation.findCheapestBestRatedHotel(dates);

        assertEquals("Bridgewood, Rating: 4 and Total Rates: $200", result);
    }
}