package test.java;

import com.bridgelabz.hotelreservation.Hotel;
import com.bridgelabz.hotelreservation.HotelReservation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HotelReservationTest {

    @Test
    void givenHotelRates_WhenAdded_ShouldStoreCorrectly() {

        HotelReservation reservation = new HotelReservation();

        reservation.addHotel("Lakewood", 110, 90);

        Hotel hotel = reservation.hotelList.get(0);

        assertEquals("Lakewood", hotel.name);
        assertEquals(110, hotel.weekdayRate);
        assertEquals(90, hotel.weekendRate);
    }
}