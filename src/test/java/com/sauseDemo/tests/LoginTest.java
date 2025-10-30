package com.sauseDemo.tests;

import com.sauseDemo.base.BaseClass;
import com.sauseDemo.pages.LoginPage;
import com.sauseDemo.pages.ProductListPage;
import com.sauseDemo.util.DataDrivenProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.Log;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseClass {

    @Test
    public void testVerifyWithValidLoginCredentials(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername("standard_user").typePassword("secret_sauce").clickLoginBtn();

        ProductListPage productListPage = new ProductListPage(driver);
        Assert.assertEquals(productListPage.getTitleText(),"Products", "Titles are not matching");
    }

    @Test(dataProvider = "login-credentials",dataProviderClass = DataDrivenProvider.class, description = "verifyInvalidLogin")
    public void testVerifyWithInvalidLoginCredentials(String username, String password, String errorMessage){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername(username).typePassword(password).clickLoginBtn();
        Assert.assertEquals(loginPage.getError(), errorMessage, "Error messages are not matching");
    }


    @Test
    public void testVerifyLoginWithLockedOutUser(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername("locked_out_user").typePassword("secret_sauce").clickLoginBtn();
        Assert.assertEquals(loginPage.getError(),"Epic sadface: Sorry, this user has been locked out.", "Error massage not matching");

    }

    @Test
    public void testVerifyLoginWithPerformanceGlitchUser(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername("performance_glitch_user").typePassword("secret_sauce").clickLoginBtn();

        ProductListPage productListPage = new ProductListPage(driver);
        Assert.assertEquals(productListPage.getTitleText(),"Products", "Expected Title not matching");
    }

    @Test
    public void testVerifyPlaceholderNames(){
        LoginPage loginPage = new LoginPage(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(loginPage.getUserNamePlaceholderName(),"Username", "Expected Placeholder names are not matching");
        softAssert.assertEquals(loginPage.getPasswordPlaceholderName(), "Password", "Expected Placeholder names are not matching");
        softAssert.assertAll();
    }
}
