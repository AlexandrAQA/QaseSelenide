package classes;

import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.id;

public class LoginTest {

    @Test
    public void testLogin(){
        //1.open URL
        open("https://app.qase.io/login");
        //2.Find the elements by ID. FullXpath: //input[@id='inputEmail'/'inputPassword']
        $(id("inputEmail")).sendKeys("mrgmgrv@gmail.com");
        $(id("inputPassword")).sendKeys("Selenide23+");
        $(id("btnLogin")).click();

    }
}
