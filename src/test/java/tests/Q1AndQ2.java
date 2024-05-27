package tests;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.MainPage;
import pageObjects.ProductPage;
import pojos.Q2Pojo;


public class Q1AndQ2 extends BaseTest {

    @Test(groups = "browserTests")
    public void q1(){
        driver.get(testData.getEbayUrl());
        MainPage mainPage = new MainPage(driver);
        String productTextFromMainPage = mainPage.clickOnMacbookPage();
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCartAndGoToCartPage();
        CartPage cartPage = new CartPage(driver);
        String productTextFromCartPage = cartPage.getTextProductFromCartPage();
        Assert.assertEquals(productTextFromMainPage, productTextFromCartPage);
    }

    @Test
    public void q2() throws Exception {
        Q2Pojo q2Pojo = new Q2Pojo(testData.getQ2Pojo().getId(), testData.getQ2Pojo().getPetId(), testData.getQ2Pojo().getQuantity(), testData.getQ2Pojo().getShipDate(), testData.getQ2Pojo().getStatus(), testData.getQ2Pojo().isComplete());
        Q2Pojo responsePojo = runApiAndVerifyStatus(testData.getPetstoreUrl(), q2Pojo);
        Assert.assertEquals(responsePojo.getStatus(), testData.getQ2Pojo().getStatus(), "Status is not placed");
    }
}

