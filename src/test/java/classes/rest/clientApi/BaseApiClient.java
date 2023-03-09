package classes.rest.clientApi;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseApiClient {

    //restAssured lib
    RequestSpecification requestSpecification;

    public BaseApiClient(){
        requestSpecification = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                log().ifValidationFails();
    }


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

    public Response get (String uri, Map<String, ?> parameterNameValuePairs){
        return given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                log().ifValidationFails().
                pathParams(parameterNameValuePairs)
                .when()
                .get("https://petstore.swagger.io/" + uri)
                .then().
                log().ifValidationFails(). //to see logs
                extract().
                response();
    }


}
