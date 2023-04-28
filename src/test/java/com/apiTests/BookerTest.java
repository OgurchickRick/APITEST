package com.apiTests;

import io.restassured.response.Response;
import model.bookerModel.Bookingdates;
import model.bookerModel.Booking;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static specification.SpecificationsBooker.requestSpecificationBooker;
import static specification.SpecificationsBooker.responseSpecificationBooker;
import static utils.BookingUtils.assertBookingEquals;

public class BookerTest {


    @Test(description = "Тестриование запроса GET, возвращает идентификаторы всех бронирований")
    public void  getBookingIds() {
        Response response = given()
                .spec(requestSpecificationBooker())
                .get("/booking")
                .then().spec(responseSpecificationBooker(200))
                .extract().response();
        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        Assert.assertFalse(bookingIds.isEmpty());
    }

    @Test
    public void getBooking() {
        given()
                .spec(requestSpecificationBooker())
                .get("/booking/1")
                .then().spec(responseSpecificationBooker(200));
    }

    @Test(description = "Тестирование запроса POST на добавление бронирования")
    public void createBooking() {
        Bookingdates date = new Bookingdates("2018-01-01", "2019-01-01");
        Booking reqBody = new Booking("Jim", "Brown", 111, true, date, "Breakfast");
        Booking booking = given()
                .spec(requestSpecificationBooker())
                .body(reqBody)
                .post("/booking")
                .then().spec(responseSpecificationBooker(200))
                .extract().body().jsonPath().getObject("booking", Booking.class);
        assertBookingEquals(booking, reqBody);
    }

    @Test
    public void updateBooking() {
        Bookingdates date = new Bookingdates("2018-01-01", "2019-01-01");
        Booking reqBody = new Booking("James", "Brown", 111, true, date, "Breakfast");
        Booking booking = given()
                .spec(requestSpecificationBooker())
                .body(reqBody)
                .post("/booking/1")
                .then().spec(responseSpecificationBooker(200))
                .extract().body().jsonPath().getObject("", Booking.class);
        assertBookingEquals(booking, reqBody);
    }

    @Test
    public void  deleteBooking() {
        given()
                .spec(requestSpecificationBooker())
                .delete("booker/1")
                .then().spec(responseSpecificationBooker(200));
    }

    @Test
    public void healthCheck() {
        given()
                .spec(requestSpecificationBooker())
                .get("/ping")
                .then().log().status()
                .statusCode(201);
    }


}
