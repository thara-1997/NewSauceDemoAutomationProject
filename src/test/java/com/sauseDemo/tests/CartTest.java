package com.sauseDemo.tests;

import com.sauseDemo.base.BaseClass;
import com.sauseDemo.pages.CartPage;
import com.sauseDemo.pages.ProductListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseClass {

    @Test
    public void testVerifyProductDetailsInCart(){
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.clickSauceLabsBackPackBtn();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCartBtn();
        Assert.assertEquals(cartPage.getCartItemName(),"Sauce Labs Backpack", "Expected product is not matching");
    }

    @Test
    public void testVerifyRemoveCartItems(){
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.clickSauceLabsBackPackBtn();
        productListPage.clickSauceLabsBikeLightBtn();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCartBtn();
        cartPage.clickRemoveSauceLabsBackPack();
        Assert.assertEquals(cartPage.getCartItemCount().size(),1,"Expected count is not matching");

    }

    @Test
    public void testVerifyContinueShoppingButton(){
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCartBtn();
        cartPage.clickContinueShoppingButton();
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/inventory.html"));
    }

    @Test
    public void testVerifyCheckoutButton(){
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.clickSauceLabsBikeLightBtn();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCartBtn();
        cartPage.clickCheckoutButton();
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/checkout-step-one.html"));
    }
}
