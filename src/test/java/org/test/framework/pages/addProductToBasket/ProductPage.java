package org.test.framework.pages.addProductToBasket;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.test.framework.pages.AbstractPage;

import java.time.Duration;

public class ProductPage extends AbstractPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By productPageTitleElement = By.xpath("//*[@class = 'app_logo']");
    private final By openBasketElement = By.xpath("//*[@class = 'shopping_cart_link']");

    private By addProductToBasketElement(String productName) {
        String xPath = String.format("//div[contains(text(), \"%s\")]/parent::a/parent::div/following-sibling::div[@class='pricebar']/button", productName);
        return By.xpath(xPath);
    }

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
    }

    @Override
    public void openPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @Step("Get added product title")
    public String getProductPageTitle() {
        WebElement productTitleEl = wait.until(ExpectedConditions.presenceOfElementLocated(productPageTitleElement));
        return productTitleEl.getAttribute("innerText").trim();
    }

    @Step("Add product to the basket")
    public void addProductToTheBasket(String productName) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(addProductToBasketElement(productName)));
        wait.until(ExpectedConditions.presenceOfElementLocated(addProductToBasketElement(productName))).click();
    }

    @Step("Navigate to basket")
    public BasketPage navigateToBasket() {
        driver.findElement(openBasketElement).click();
        return new BasketPage(driver);
    }
}
