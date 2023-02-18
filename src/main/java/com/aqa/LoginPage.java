package com.aqa;

import com.codeborne.selenide.Selenide;

public class LoginPage {

    public LoginPage openLoginPage(){
        Selenide.open("/login");
        return new LoginPage();
    }
}
