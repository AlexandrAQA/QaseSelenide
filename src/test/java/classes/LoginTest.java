package classes;

import com.aqa.LoginPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.id;

public class LoginTest {

    @Test
    public void testLogin() {
        new LoginPage().openLoginPage()
                       .login();

        //'Should' is not checking! it's just for sure
        $(id("createButton")).shouldBe(visible);
        //only assert checks
        assertThat($(id("createButton")).getText()).isNotNull().isEqualTo("Create new project");
    }
}
