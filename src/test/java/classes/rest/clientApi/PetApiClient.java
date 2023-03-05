package classes.rest.clientApi;

import com.aqa.model.Pet;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class PetApiClient {

    public Pet postPet(Pet pet){
        return given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(pet).
                log().ifValidationFails().
                when().
                post("https://petstore.swagger.io/v2/pet")
                .then().
                statusCode(200). //check status code
                log().ifValidationFails(). //to see logs
                extract().
                body().as(Pet.class);

    }
}
