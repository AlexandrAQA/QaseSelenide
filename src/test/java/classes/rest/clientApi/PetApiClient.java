package classes.rest.clientApi;

import com.aqa.model.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class PetApiClient extends BaseApiClient{

    public Pet post(Pet pet){
        Response response = post("v2/pet", pet);
        return response.then()
                .statusCode(200)
                .extract()
                .body()
                .as(Pet.class);

    }

    public Pet getPet(long petId){
        Response response = get("v2/pet/{petId}", Map.of("petId", petId));
        return given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                log().ifValidationFails().
                pathParams(Map.of("petId",petId)).
                             when().
                             get("v2/pet/{petId}")
                             .then().
                             //statusCode(200).
                             extract().
                             body().as(Pet.class);
    }


}
