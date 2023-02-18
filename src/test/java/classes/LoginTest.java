package classes;

import org.assertj.core.api.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.id;

public class LoginTest {

    @Test
    public void testLogin() {
        //1.open URL
        open("https://app.qase.io/login");
        //2.Find the elements by ID. FullXpath: //input[@id='inputEmail'/'inputPassword']
        $(id("inputEmail"))

                //3.shouldBe(Condition.enabled). => we can verify that elem is enabled
                //and waiter is here too
                .shouldBe(enabled)

                //send email to the field
                .sendKeys("mrgmgrv@gmail.com");

        $(id("inputPassword")).sendKeys("Selenide23+");
        $(id("btnLogin")).click();

        //'Should' is not checking! it's just for sure
        $(id("createButton")).shouldBe(visible);

        //only assert is check
        assertThat($(id("createButton")).getText()).isNotNull()
                                                   .isEqualTo("Create new project");

    }
}
