package utils;

import model.bookerModel.Booking;
import org.testng.Assert;

public class BookingUtils {

    public static void assertBookingEquals(Booking actualBooking, Booking expectedBooking) {
        Assert.assertEquals(expectedBooking.getFirstname(), actualBooking.getFirstname());
        Assert.assertEquals(expectedBooking.getLastname(), actualBooking.getLastname());
    }
}
