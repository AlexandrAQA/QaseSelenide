package classes.rest;

import com.aqa.model.Category;
import com.aqa.model.Pet;
import com.aqa.model.Tag;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PetTest {

    @Test
    public void createPetTest(){
        final Pet expectedPet = Pet.builder()
                             .name("Pet Test Name")
                             .category(Category.builder()
                                               .name("Test Category")
                                               .build())
                             .photoUrls(List.of("Test Urls 1", "Test Urls 2"))
                             .tags(List.of(Tag.builder()
                                              .name("Test Tag")
                                              .build()))
                             .status("available")
                             .build();
        //POST Pet
        long id = given().
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
                extract().body().jsonPath().getLong("id"); //extract id from the response

    //GET PET /pet/{petId}
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                log().all().
                pathParam("petId",id).
        when().
                get("https://petstore.swagger.io/v2/pet/{petId}")
        .then().
                statusCode(200).
                log().body().
                log().status(); //check status code

    }
}
