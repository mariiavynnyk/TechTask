package org.test.basePackage;

import io.qameta.allure.Description;
import org.test.framework.pages.Checkout.CheckoutPage;
import org.test.framework.pages.addProductToBasket.BasketPage;
import org.test.framework.pages.addProductToBasket.ProductPage;
import org.test.framework.pages.login.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class SwagLabsTest extends BaseTest {
    private LoginPage loginPage;
    private CheckoutPage checkoutPage;
    private BasketPage basketPage;
    private ProductPage productPage;

    private static final String EMAIL = "standard_user";
    private static final String PASSWORD = "secret_sauce";
    private static final String PRODUCT_NAME_1 = "Sauce Labs Onesie";
    private static final String PRODUCT_NAME_2 = "Sauce Labs Backpack";
    private static final String PRODUCT_NAME_3 = "Sauce Labs Fleece Jacket";
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @BeforeClass
    public void beforeClass() {
        loginPage = new LoginPage(driver);
        loginPage.openPage();
    }

    @Test
    @Description("Verify user can log in and product page is opened")
    public void verifyProductsPageIsOpenedAfterLogIn() {
        productPage = new ProductPage(driver);
        loginPage.logIn(EMAIL, PASSWORD);

        Assert.assertEquals(productPage.getProductPageTitle(), "Swag Labs",
                "Products page should be opened.");
    }

    @Test(dependsOnMethods = "verifyProductsPageIsOpenedAfterLogIn")
    public void verifyAddedProductsInTheBasket() {
        List<String> productTitles = Arrays.asList(PRODUCT_NAME_1, PRODUCT_NAME_2, PRODUCT_NAME_3);
        productTitles.forEach(name -> productPage.addProductToTheBasket(name));
        basketPage = productPage.navigateToBasket();

        Assert.assertTrue(productTitles.containsAll(basketPage.getProductsTitle()),
                String.format("Products '%s' should be shown in the basket.", productTitles));
    }

    @Test(dependsOnMethods = "verifyAddedProductsInTheBasket")
    public void verifyRemovedProductIsNotPresentInTheBasket() {
        basketPage.removeProductFromTheBasket(PRODUCT_NAME_1);

        Assert.assertFalse(basketPage.isProductShown(PRODUCT_NAME_1),
                String.format("Product '%s' should not be present in the basket.", PRODUCT_NAME_1));
    }

    @Test(dependsOnMethods = "verifyRemovedProductIsNotPresentInTheBasket")
    public void verifyTheTotalPrice() {
        double firstPrice = basketPage.getProductPrice(PRODUCT_NAME_2);
        double secondPrice = basketPage.getProductPrice(PRODUCT_NAME_3);

        checkoutPage = basketPage.clickOnCheckoutButton();
        checkoutPage.fillInformationFieldsIfNeededAndContinue("Test", "User", 12345);

        double tax = basketPage.getTax();
        double expectedTotalPrice = Double.parseDouble(df.format(firstPrice + secondPrice + tax));
        double actualTotalPrice = basketPage.getTotalPrice();

        Assert.assertEquals(expectedTotalPrice, actualTotalPrice,
                String.format("Total price should be '%s'", expectedTotalPrice));
    }

    @Test(dependsOnMethods = "verifyTheTotalPrice")
    public void verifyTheOrderIsCreated() {
        checkoutPage = basketPage.clickOnFinishButton();

        Assert.assertTrue(checkoutPage.isSuccessCheckoutMessageShown(),
                "'Checkout: Complete!' title should be shown.");
    }
}
