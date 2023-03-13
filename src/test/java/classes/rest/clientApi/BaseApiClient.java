package classes.rest.clientApi;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseApiClient {

    //restAssured lib
    RequestSpecification requestSpecification;
    String BASE_PATH = "https://petstore.swagger.io/";

    public BaseApiClient(){
        requestSpecification = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().ifValidationFails();
    }


    public Response post(String uri, Object body){
        return requestSpecification.body(body)
                                   .when()
                                   .post(BASE_PATH + uri)
                                   .then()
                                   .log().ifValidationFails() //to see logs
                                   .extract()
                                   .response();
    }

    public Response get (String uri, Map<String, ?> parameterNameValuePairs){
        return requestSpecification
                .pathParams(parameterNameValuePairs)
                .when()
                .get(BASE_PATH + uri)
                .then()
                .log().ifValidationFails() //to see logs
                .extract()
                .response();
    }

    public Response put(String uri, Object body){
        return requestSpecification.body(body)
                                   .when()
                                   .put(BASE_PATH + uri)
                                   .then()
                                   .log().ifValidationFails() //to see logs
                                   .extract()
                                   .response();
    }
//    public Response delete (String uri, Map<String, ?> parameterNameValuePairs){
//        return requestSpecification
//                .pathParams(parameterNameValuePairs)
//                .when()
//                .delete(BASE_PATH + uri)
//                .then()
//                .log().ifValidationFails() //to see logs
//                .extract()
//                .response();
//    }


}
