package com.apiTests;

import model.Unknown;
import model.User;
import model.UserData;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static specification.SpecificationsReqres.requestSpecificationReqres;
import static io.restassured.RestAssured.given;

public class ReqresTest {

    @Test(description = "Тестирование запроса GET с проверкой что поля Avatar пользователей страницы содержат в себе ID этого же пользователя")
    public void checkAvatarAndIdTest(){
        List<UserData> users = given()
                .spec(requestSpecificationReqres())
                .get("/api/users?page=2")
                .then().log().status()
                .statusCode(200)
                .extract().body().jsonPath().getList("data", UserData.class);
        users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
    }

    @Test(description = "Тестирование запроса GET с несуществующим пользователем")
    public void getUserNotFound() {
        given()
                .spec(requestSpecificationReqres())
                .get("/api/users/99")
                .then().log().status()
                .statusCode(404)
                .body(Matchers.anything());
    }


    @Test(description = "Тестирование запроса GET с проверкой что поле Avatar пользователя содержат в себе ID этого же пользователя")
    public void getUser() {
        UserData user = given()
                .spec(requestSpecificationReqres())
                .get("api/users/2")
                .then().log().status()
                .statusCode(200)
                .extract().body().jsonPath().getObject("data", UserData.class);
        Assert.assertTrue(user.getAvatar().contains(user.getId().toString()));
    }


    @Test(description = "Тестирование запроса POST на создание пользователя")
    public void createUser() {
        User reqBody = new User("Ivan", "God");
        User user = given()
                .spec(requestSpecificationReqres())
                .body(reqBody)
                .post("api/users")
                .then().log().status()
                .statusCode(201)
                .extract().body().jsonPath().getObject("", User.class);
        Assert.assertTrue(user.getName().equals(reqBody.getName()) && user.getJob().equals(reqBody.getJob()));
    }


    @Test(description = "Тестирование запроса PUT на изменение пользователя")
    public  void updateUser() {
        User reqBody = new User("Ivan", "God");
        User user = given()
                .spec(requestSpecificationReqres())
                .body(reqBody)
                .put("api/users/2")
                .then().log().status()
                .statusCode(200)
                .extract().body().jsonPath().getObject("", User.class);
        Assert.assertTrue(user.getName().equals(reqBody.getName()) && user.getJob().equals(reqBody.getJob()));
    }


    @Test(description = "Тестирование запроса DELETE на удаление пользователся")
    public void deleteUser() {
        given()
                .spec(requestSpecificationReqres())
                .delete("api/users/2")
                .then().log().status()
                .statusCode(204);
    }


    @Test
    public void getUnknown() {
        Unknown unknown = given()
                .spec(requestSpecificationReqres())
                .get("api/unknown/2")
                .then().log().status()
                .statusCode(200)
                .extract().body().jsonPath().getObject("data", Unknown.class);
        Assert.assertEquals(2, (int) unknown.getId());
    }

    @Test
    public void getUnknownList() {
        given()
                .spec(requestSpecificationReqres())
                .get("api/unknown")
                .then().log().status()
                .statusCode(200);
    }

    @Test
    public void getUnknownNotFound() {
        given()
                .spec(requestSpecificationReqres())
                .get("/api/unknown/99")
                .then().log().status()
                .statusCode(404)
                .body(Matchers.anything());
    }
}
