package test.java;

import com.bridgelabz.hotelreservation.CustomerType;
import com.bridgelabz.hotelreservation.Hotel;
import com.bridgelabz.hotelreservation.HotelReservation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HotelReservationTest {


    @Test
    void givenRegularCustomer_ShouldReturnBestHotel() {

        HotelReservation reservation = new HotelReservation();

        reservation.addHotel("Lakewood", 110, 90, 80, 80, 3);
        reservation.addHotel("Bridgewood", 150, 50, 110, 50, 4);
        reservation.addHotel("Ridgewood", 220, 150, 100, 40, 5);

        String[] dates = {"11Sep2020", "12Sep2020"};

        String result = reservation.findCheapestBestRatedHotel(dates, CustomerType.REGULAR);

        assertEquals("Bridgewood, Rating: 4, Total Cost: $200", result);
    }
    @Test
    void givenRewardCustomer_ShouldReturnBestHotel() {

        HotelReservation reservation = new HotelReservation();

        reservation.addHotel("Lakewood", 110, 90, 80, 80, 3);
        reservation.addHotel("Bridgewood", 150, 50, 110, 50, 4);
        reservation.addHotel("Ridgewood", 220, 150, 100, 40, 5);

        String[] dates = {"11Sep2020", "12Sep2020"};

        String result = reservation.findCheapestBestRatedHotel(dates, CustomerType.REWARD);

        assertEquals("Ridgewood, Rating: 5, Total Cost: $140", result);
    }
}