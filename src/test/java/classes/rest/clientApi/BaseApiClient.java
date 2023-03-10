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
        requestSpecification = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().ifValidationFails()
                .basePath("https://petstore.swagger.io/");
    }


    public Response post(String uri, Object body){
        return requestSpecification.body(body)
                                   .when()
                                   .post(uri)
                                   .then()
                                   .log().ifValidationFails() //to see logs
                                   .extract()
                                   .response();
    }

    public Response get (String uri, Map<String, ?> parameterNameValuePairs){
        return requestSpecification
                .pathParams(parameterNameValuePairs)
                .when()
                .get(uri)
                .then()
                .log().ifValidationFails() //to see logs
                .extract()
                .response();
    }


}
