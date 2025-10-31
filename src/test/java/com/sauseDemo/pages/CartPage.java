package com.sauseDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.List;

public class CartPage {

    private final WebDriver driver;

    private final By byCartItem= By.xpath("//div[@class= 'inventory_item_name']");
    private final By byCartBtn = By.xpath("//a[@class= 'shopping_cart_link']");
    private final By byRemoveCartBtn = By.xpath("//button[@class='btn btn_secondary btn_small cart_button']");

    private final By byRemoveSauceLabsBackPack = By.id("remove-sauce-labs-backpack");
    private final By byContinueShoppingBtn = By.id("continue-shopping");
    private final By byCheckoutBtn = By.id("checkout");

    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    public List<WebElement> getCartItemCount(){
        return driver.findElements(byCartItem);
    }

    public void clickCartBtn(){
        driver.findElement(byCartBtn).click();
    }

    public void clickRemoveBtn(){
        driver.findElement(byRemoveCartBtn).click();
    }
    public String getCartItemName(){
        return driver.findElement(byCartItem).getText();
    }

    public void clickRemoveSauceLabsBackPack(){
        driver.findElement(byRemoveSauceLabsBackPack).click();
    }
    public void clickContinueShoppingButton(){
        driver.findElement(byContinueShoppingBtn).click();
    }
    public void clickCheckoutButton(){
        driver.findElement(byCheckoutBtn).click();
    }
}
