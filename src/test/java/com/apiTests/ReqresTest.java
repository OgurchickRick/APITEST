package com.apiTests;

import com.model.UserData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static com.specification.Specifications.requestSpecification;
import static io.restassured.RestAssured.given;

public class ReqresTest {


    @Test
    @DisplayName("Тестирование запроса GET с проверкой что поле Avatar содержит в себе ID")
    public void checkAvatarAndIdTest(){
        List<UserData> users = given()
                .spec(requestSpecification())
                .get("/api/users?page=2")
                .then().log().status()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
    }
}
