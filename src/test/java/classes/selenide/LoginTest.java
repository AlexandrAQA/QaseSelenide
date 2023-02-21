package classes.selenide;

import com.aqa.LoginPage;
import com.codeborne.selenide.testng.ScreenShooter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.id;

@Listeners({ScreenShooter.class})
public class LoginTest {

    @Test
    public void testLogin() {
        ScreenShooter.captureSuccessfulTests = true;
        new LoginPage().openLoginPage()
                       .login();

        //'Should' is not checking! it's just for sure
        $(id("createButton")).shouldBe(visible);
        //only assert checks
        assertThat($(id("createButton")).getText()).isNotNull().isEqualTo("Create new project");
        $x("//table").$$x("//tr[@class='project-row']//a")
                     .filter(exactText("Test"));
    }
}
