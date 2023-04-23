package com.apiTests;

import model.Bookingdates;
import model.Booking;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static specification.SpecificationsBooker.requestSpecificationBooker;
import static specification.SpecificationsBooker.responseSpecificationBooker;

public class BookerTest {


    @Test(description = "Тестриование запроса GET, возвращает идентификаторы всех бронирований")
    public void  getBookingIds() {
        given()
                .spec(requestSpecificationBooker())
                .get("/booking")
                .then().log().status()
                .statusCode(200);
    }

    @Test
    public void getBooking() {
        given()
                .spec(requestSpecificationBooker())
                .get("/booking/1")
                .then().log().status()
                .statusCode(200);
    }

    @Test(description = "Тестирование запроса POST на добавление бронирования")
    public void createBooking() {
        Bookingdates date = new Bookingdates("2018-01-01", "2019-01-01");
        Booking reqBody = new Booking("Jim", "Brown", 111, true, date, "Breakfast");
        Booking booking = given()
                .spec(requestSpecificationBooker())
                .body(reqBody)
                .post("/booking")
                .then().spec(responseSpecificationBooker())
                .extract().body().jsonPath().getObject("booking", Booking.class);
        Assert.assertTrue(booking.firstname.equals(reqBody.firstname) && booking.lastname.equals(reqBody.lastname));
    }

    @Test
    public void updateBooking() {
        Bookingdates date = new Bookingdates("2018-01-01", "2019-01-01");
        Booking reqBody = new Booking("James", "Brown", 111, true, date, "Breakfast");
        Booking booking = given()
                .spec(requestSpecificationBooker())
                .body(reqBody)
                .post("/booking/1")
                .then().spec(responseSpecificationBooker())
                .extract().body().jsonPath().getObject("", Booking.class);
        Assert.assertTrue(booking.firstname.equals(reqBody.firstname) && booking.lastname.equals(reqBody.lastname));
    }

    @Test
    public void  deleteBooking() {
        given()
                .spec(requestSpecificationBooker())
                .delete("booker/1")
                .then().spec(responseSpecificationBooker());
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
