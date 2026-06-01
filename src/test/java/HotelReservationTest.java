package test.java;

import com.bridgelabz.hotelreservation.HotelReservation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HotelReservationTest {

    @Test
    public void givenHotelDetails_WhenAdded_ShouldReturnTrue() {
        HotelReservation reservation = new HotelReservation();
        reservation.addHotel("Lakewood", 110);

        boolean result = !reservation.hotelList.isEmpty();

        assertTrue(result);
    }
    @Test
    void givenSingleDay_ShouldReturnCheapestHotel() {

        HotelReservation reservation = new HotelReservation();
        reservation.addHotel("Lakewood", 110);
        reservation.addHotel("Bridgewood", 150);

        String result = reservation.findCheapestHotel(1);

        assertEquals("Lakewood, Total Rates: $110", result);
    }
    @Test
    void givenNoHotels_ShouldReturnEmpty() {

        HotelReservation reservation = new HotelReservation();

        String result = reservation.findCheapestHotel(2);

        assertEquals(", Total Rates: $" + Integer.MAX_VALUE, result);
    }
}