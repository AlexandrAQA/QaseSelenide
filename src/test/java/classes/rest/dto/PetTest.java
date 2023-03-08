package classes.rest.dto;

import classes.rest.clientApi.PetApiClient;
import com.aqa.model.Category;
import com.aqa.model.Pet;
import com.aqa.model.Tag;
import org.testng.annotations.Test;

import java.util.List;

import static com.aqa.model.Status.available;
import static org.assertj.core.api.Assertions.assertThat;

public class PetTest {

    PetApiClient petApiClient = new PetApiClient();

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
        Pet postActualPet = petApiClient.post(expectedPet); //extract actualPet from the response
        assertPet(expectedPet, postActualPet);


        //GET PET /pet/{petId}
        Pet getActualPet = petApiClient.getPet(postActualPet.getId());
        //check status code
        assertPet(expectedPet, getActualPet);


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
