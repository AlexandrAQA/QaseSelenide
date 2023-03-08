package classes.rest.clientApi;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseApiClient {

    public Response post(String uri, Object body){
        return given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(body).
                log().ifValidationFails().
                when().
                post("https://petstore.swagger.io/" + uri)
                .then().
                log().ifValidationFails(). //to see logs
                extract().
                response();
    }
}
