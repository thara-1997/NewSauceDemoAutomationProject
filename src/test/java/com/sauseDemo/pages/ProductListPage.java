package com.sauseDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.List;

public class ProductListPage {
    private final WebDriver driver;

    private final By byTitle = By.xpath("//span[@class = 'title']");

    private final By bySauceLabsBackPackPrice = By.xpath("//div[text()= '29.99']");
    private final By bySauceLabsBackPackTitle = By.xpath("//div[normalize-space()='Sauce Labs Backpack']");
    private final By byAddToCartBtn = By.xpath("//button[@class = 'btn btn_primary btn_small btn_inventory ']");
    private final By byRemoveBtn = By.xpath("//button[@class = 'btn btn_secondary btn_small btn_inventory ']");
    private final By byCartCount = By.xpath("//span[@class ='shopping_cart_badge']");
    private final By bySelectFilter = By.xpath("//select[@class = 'product_sort_container']");
    private final By byAllProducts = By.xpath("//div[@class = 'inventory_item_description']");
    private final By byPriceList = By.xpath("//div[@class = 'inventory_item_price']");

    private final By bySauceLabsBackPackBtn = By.id("add-to-cart-sauce-labs-backpack");
    private final By bySauceLabsBikeLightBtn = By.id("add-to-cart-sauce-labs-bike-light");

    public ProductListPage(WebDriver driver){
        this.driver = driver;
    }

    public String getTitleText(){
        return driver.findElement(byTitle).getText();
    }

    public String getSauceLabsBackPackTitleText(){
        return driver.findElement(bySauceLabsBackPackTitle).getText();
    }
    public String getSauceLabsBackPackPrice(){
        return driver.findElement(bySauceLabsBackPackPrice).getText();
    }

    public List<WebElement> clickAddToCartBtn(){
        return driver.findElements(byAddToCartBtn);
    }

    public List<WebElement> clickRemoveBtn(){
        return driver.findElements(byRemoveBtn);
    }

    public String  getCartCount(){
        return driver.findElement(byCartCount).getText();
    }

    public ProductListPage selectFilter(String value){
        WebElement filter = driver.findElement(bySelectFilter);
        Select select = new Select(filter);
        select.selectByValue(value);
        return this;
    }

    public List<WebElement> getAllProductDetails(){
        return driver.findElements(byAllProducts);
    }

    public List<WebElement> getPriceList(){
        return driver.findElements(byPriceList);
    }

    public void clickSauceLabsBackPackBtn(){
        driver.findElement(bySauceLabsBackPackBtn).click();
    }
    public void clickSauceLabsBikeLightBtn(){
        driver.findElement(bySauceLabsBikeLightBtn).click();
    }
}
