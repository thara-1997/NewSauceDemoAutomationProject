package com.sauseDemo.base;

import com.sauseDemo.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

public class BaseClass {
    protected WebDriver driver;
    @BeforeMethod
    public void beforeMethod(){
        ChromeOptions options = new ChromeOptions();

// Disable password manager
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-popup-blocking");

// Disable Chrome’s password manager
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

       driver = new ChromeDriver(options);
       driver.manage().window().maximize();
       driver.get("https://www.saucedemo.com/");
       login();
    }

    public void login(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername("standard_user").typePassword("secret_sauce").clickLoginBtn();
        ChromeOptions options = new ChromeOptions();
    }
    @AfterMethod
    public void afterMethod(){
        //driver.quit();
    }
}
