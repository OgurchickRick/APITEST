package specification;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

import java.util.concurrent.TimeUnit;

public class SpecificationsReqres {

    public static RequestSpecification requestSpecificationReqres() {
        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in")
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecificationReqres() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .expectContentType(ContentType.JSON)
                .expectStatusCode(HttpStatus.SC_OK)
                .expectResponseTime(Matchers.lessThanOrEqualTo(3L), TimeUnit.SECONDS)
                .build();
    }
}
