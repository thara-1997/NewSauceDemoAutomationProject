package com.sauseDemo.tests;

import com.sauseDemo.base.BaseClass;
import com.sauseDemo.pages.ProductListPage;
import io.cucumber.java.eo.Do;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

public class ProductListTest extends BaseClass {

    @Test
    public void testVerifyProductListPage(){
        ProductListPage productListPage = new ProductListPage(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(productListPage.getSauceLabsBackPackTitleText(),"Sauce Labs Backpack","Expected product name is not matching");
        softAssert.assertEquals(productListPage.getSauceLabsBackPackPrice(),"$29.99","Expected price is not matching");
        softAssert.assertAll();
    }

   @Test
    public void testVerifyClickEachAddToCartButton(){
        ProductListPage productListPage = new ProductListPage(driver);
        for(WebElement addToCartBtn: productListPage.clickAddToCartBtn()){
            addToCartBtn.click();
        }
       Assert.assertEquals(productListPage.getCartCount(),"6","Expected Count is not matching");
   }

   @Test
    public void testVerifyClickRemoveButton(){
        ProductListPage productListPage = new ProductListPage(driver);
       for(WebElement addToCartBtn: productListPage.clickAddToCartBtn()){
           addToCartBtn.click();
       }
       for (WebElement removeBtn: productListPage.clickRemoveBtn()){
           removeBtn.click();
       }
       for (WebElement text: productListPage.clickAddToCartBtn()){
           Assert.assertEquals(text.getText(),"Add to cart", "Expected texts are not matching");
       }
   }

   @Test
    public void testVerifyAToZFilter(){
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.selectFilter("az");

        String previousproduct = "";
        for (WebElement product: productListPage.getAllProductDetails()){
            String currentProduct = product.getText();
            Assert.assertTrue(currentProduct.compareTo(previousproduct)>=0, "Products are not sorted in ascending order (A to Z). Current:"
                    +currentProduct+ "previousProduct" +previousproduct);
            previousproduct = currentProduct;
        }
   }

   @Test
    public void testVerifyZToAFilter(){
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.selectFilter("za");

        String previousProduct = productListPage.getAllProductDetails().get(0).getText();
        for(WebElement product: productListPage.getAllProductDetails()){
            String currentProduct = product.getText();
            Assert.assertTrue(currentProduct.compareTo(previousProduct)<=0, "Products are not sorted in ascending order (A to Z). Current:"
            +currentProduct+ "previousProduct" +previousProduct);
            previousProduct = currentProduct;
        }
   }

   @Test
    public void testVerifyPriceHighToLow(){
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.selectFilter("hilo");

        double previousValue = Double.MAX_VALUE;
        for (WebElement value: productListPage.getPriceList()){
         String priceText = value.getText().replace("$","").trim();
         double currentValue = Double.parseDouble(priceText);
         Assert.assertTrue(currentValue<=previousValue, "Products are not sorted in ascending order (high to low) Current Price: "
                 +currentValue+ "previousValue:" +previousValue);
         previousValue = currentValue;
        }
   }

    @Test
    public void testVerifyPriceLowToHigh(){
     ProductListPage productListPage = new ProductListPage(driver);
     productListPage.selectFilter("lohi");

     double previousValue = 0.0;
     for (WebElement value: productListPage.getPriceList()){
         String priceText = value.getText().replace("$","").trim();
         double currentValue = Double.parseDouble(priceText);
         Assert.assertTrue(currentValue>=previousValue,"Products are not sorted in ascending order (low to high) Current Price: "
         +currentValue+ "previousValue:" +previousValue);
         previousValue = currentValue;
     }
    }

}
