package com.apiTests;

import model.reqresModel.Unknown;
import model.reqresModel.User;
import model.reqresModel.UserData;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static specification.SpecificationsReqres.requestSpecificationReqres;
import static io.restassured.RestAssured.given;
import static specification.SpecificationsReqres.responseSpecificationReqres;
import static utils.UserUtils.assertUserEquals;

public class ReqresTest {

    @Test(description = "Тестирование запроса GET с проверкой что поля Avatar пользователей страницы содержат в себе ID этого же пользователя")
    public void checkAvatarAndIdTest(){
        List<UserData> users = given()
                .spec(requestSpecificationReqres())
                .get("/api/users?page=2")
                .then().spec(responseSpecificationReqres(200))
                .extract().body().jsonPath().getList("data", UserData.class);
        for (UserData user : users) {
            String avatarUrl = user.getAvatar();
            Integer userId = user.getId();

            Assert.assertTrue((avatarUrl).contains(userId.toString()));
        }
    }

    @Test(description = "Тестирование запроса GET с несуществующим пользователем")
    public void getUserNotFound() {
        given()
                .spec(requestSpecificationReqres())
                .get("/api/users/99")
                .then().spec(responseSpecificationReqres(404))
                .body(Matchers.anything());
    }


    @Test(description = "Тестирование запроса GET с проверкой что поле Avatar пользователя содержат в себе ID этого же пользователя")
    public void getUser() {
        UserData user = given()
                .spec(requestSpecificationReqres())
                .get("api/users/2")
                .then().spec(responseSpecificationReqres(200))
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
                .then().spec(responseSpecificationReqres(201))
                .extract().body().jsonPath().getObject("", User.class);
        assertUserEquals(user, reqBody);
    }


    @Test(description = "Тестирование запроса PUT на изменение пользователя")
    public  void updateUser() {
        User reqBody = new User("Ivan", "God");
        User user = given()
                .spec(requestSpecificationReqres())
                .body(reqBody)
                .put("api/users/2")
                .then().spec(responseSpecificationReqres(200))
                .extract().body().jsonPath().getObject("", User.class);
        assertUserEquals(user, reqBody);
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
                .then().spec(responseSpecificationReqres(200))
                .extract().body().jsonPath().getObject("data", Unknown.class);
        Assert.assertEquals(2, (int) unknown.getId());
    }

    @Test
    public void getUnknownList() {
        given()
                .spec(requestSpecificationReqres())
                .get("api/unknown")
                .then().spec(responseSpecificationReqres(200))
                .body(Matchers.anything());
    }

    @Test
    public void getUnknownNotFound() {
        given()
                .spec(requestSpecificationReqres())
                .get("/api/unknown/99")
                .then().spec(responseSpecificationReqres(404))
                .body(Matchers.anything());
    }
}
