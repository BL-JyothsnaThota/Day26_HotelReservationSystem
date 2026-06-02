package test.java;

import com.bridgelabz.hotelreservation.Hotel;
import com.bridgelabz.hotelreservation.HotelReservation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HotelReservationTest {

    @Test
    void givenWeekdayAndWeekendDates_ShouldReturnCheapestHotels() {

        HotelReservation reservation = new HotelReservation();

        reservation.addHotel("Lakewood", 110, 90);
        reservation.addHotel("Bridgewood", 150, 50);
        reservation.addHotel("Ridgewood", 220, 150);

        String[] dates = {"11Sep2020", "12Sep2020"};

        String result = reservation.findCheapestHotel(dates);

        assertEquals("Lakewood and Bridgewood with Total Rates $200", result);
    }
}