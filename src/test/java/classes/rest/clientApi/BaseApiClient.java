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
        //RestAssured.basePath = "https://petstore.swagger.io/";
        requestSpecification = given().
                                        contentType(ContentType.JSON).
                                        accept(ContentType.JSON).
                                        log().ifValidationFails().
                                        basePath("https://petstore.swagger.io/");
    }


    public Response post(String uri, Object body){
        return requestSpecification.
                body(body).
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
