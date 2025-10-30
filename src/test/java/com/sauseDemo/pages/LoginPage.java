package com.sauseDemo.pages;

import com.sun.jna.platform.unix.solaris.LibKstat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.security.PublicKey;

public class LoginPage {
  private final WebDriver driver;

  private final By byUsername = By.id("user-name");
  private final By byPassword = By.id("password");
  private final By byLoginBtn = By.id("login-button");

  private  final By errorText = By.cssSelector("h3[data-test = 'error']");

  private final By byUsernamePlaceholder = By.id("user-name");
  private final By byPasswordPlaceholder = By.id("password");

  public LoginPage(WebDriver driver){
   this.driver = driver;
  }

  public LoginPage typeUsername(String userName){
    driver.findElement(byUsername).sendKeys(userName);
    return this;
  }

  public LoginPage typePassword(String password){
    driver.findElement(byPassword).sendKeys(password);
    return this;
  }

  public String getError(){
    return  driver.findElement(errorText).getText();
  }

  public void clickLoginBtn(){
    driver.findElement(byLoginBtn).click();
  }

  public String getUserNamePlaceholderName(){
    return driver.findElement(byUsernamePlaceholder).getDomAttribute("placeholder");
  }

  public String getPasswordPlaceholderName(){
    return driver.findElement(byPasswordPlaceholder).getDomAttribute("placeholder");
  }
}
