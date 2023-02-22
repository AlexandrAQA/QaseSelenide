package com.aqa.ui;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.By.id;

public class LoginPage {

    public LoginPage openLoginPage(){
        Selenide.open("/login");
        getWebDriver().manage().window().maximize();
        return new LoginPage();
    }
    public LoginPage login(){
        $(id("inputEmail")).shouldBe(enabled).sendKeys("mrgmgrv@gmail.com");
        $(id("inputPassword")).sendKeys("Selenide23+");
        $(id("btnLogin")).click();
        return new LoginPage(); //TODO Change on ProjectPage
    }
}
