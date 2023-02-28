package classes.rest;

import com.aqa.model.Category;
import com.aqa.model.Pet;
import com.aqa.model.Tag;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import java.util.List;
import static com.aqa.model.Status.available;
import static io.restassured.RestAssured.given;
import static io.restassured.mapper.ObjectMapperType.GSON;
import static org.assertj.core.api.Assertions.assertThat;

public class PetTest {

    @Test
    public void createPetTest(){
        final Pet expectedPet = Pet.builder()
                             .name("MyTestDog2")
                             .category(Category.builder()
                                               .name("Test Category")
                                               .build())
                             .photoUrls(List.of("Test Urls 1", "Test Urls 2"))
                             .tags(List.of(Tag.builder()
                                              .name("Test Tag")
                                              .build()))
                             .status(available)
                             .build();
        //POST Pet
        Pet postActualPet = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(expectedPet, GSON).
                log().ifValidationFails().
       when().
               post("https://petstore.swagger.io/v2/pet")
      .then().
              statusCode(200). //check status code
                log().all(). //to see logs
                extract().
                             body().as(Pet.class); //extract actualPet from the response

        assertPet(expectedPet, postActualPet);


        //GET PET /pet/{petId}
        Pet getActualPet = given().
                                        contentType(ContentType.JSON).
                                        accept(ContentType.JSON).
                                        log().ifValidationFails().
                                        pathParam("petId", postActualPet.getId()).
                                 when().
                                        get("https://petstore.swagger.io/v2/pet/{petId}")
                                .then().
                                        statusCode(200).
                                extract().
                                        body().as(Pet.class);//check status code
        assertPet(expectedPet,getActualPet);


    }

    private void assertPet(Pet expectedPet, Pet postActualPet) {
        assertThat(postActualPet).as("The Pet is not matched expected Pet")
                                 .usingRecursiveComparison()
                                 .ignoringFields("id")
                                 .isEqualTo(expectedPet);
        assertThat(postActualPet.getId()).as("The \"id\" is not generated")
                                         .isNotNull()
                                         .isNotEqualTo(0);
    }
}
