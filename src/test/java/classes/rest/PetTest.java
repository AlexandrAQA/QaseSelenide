package classes.rest;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;

public class PetTest {

    @Test
    public void createPetTest(){
        int id = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body("{\n" +
                            "  \"id\": 0,\n" +
                            "  \"category\": {\n" +
                            "    \"id\": 0,\n" +
                            "    \"name\": \"Test Name2\"\n" +
                            "  },\n" +
                            "  \"name\": \"MyTestDog2\",\n" +
                            "  \"photoUrls\": [\n" +
                            "    \"https://ss.sport-express.ru/userfiles/materials/186/1868235/large.jpg\"\n" +
                            "  ],\n" +
                            "  \"tags\": [\n" +
                            "    {\n" +
                            "      \"id\": 0,\n" +
                            "      \"name\": \"Test\"\n" +
                            "    }\n" +
                            "  ],\n" +
                            "  \"status\": \"available\"\n" +
                            "}").
               log().all().
       when().
               post("https://petstore.swagger.io/v2/pet")
      .then().
              statusCode(200). //check status code
               body("id",not(empty()),"name", equalTo("MyTestDog2")).
               log().all(). //to see logs
               extract().body().jsonPath().getInt("id"); //extract id from the response

    }
}
