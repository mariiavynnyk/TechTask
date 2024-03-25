package org.test.framework.pages.addProductToBasket;

import io.qameta.allure.Step;
import org.apache.commons.lang.StringUtils;
import org.test.framework.pages.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class BasketPage extends AbstractPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private By productTitleElement = By.xpath("//*[@class='inventory_item_name']");

    private By removeProductFromTheBasketElement(String productName) {
        String xPath = String.format("//div[contains(text(), \"%s\")]/parent::a/parent::*//button[contains(text(), 'Remove')]", productName);
        return By.xpath(xPath);
    }

    private By productName(String productName) {
        String xPath = String.format("//*[@class='cart_item_label']//*[contains(text(), \"%s\")]", productName);
        return By.xpath(xPath);
    }

    private By itemPriceElement(String productName) {
        String xPath = String.format("  //*[@class='cart_item_label']//*[contains(text(),  \"%s\")]/parent::a/parent::div/div[@class='item_pricebar']/div", productName);
        return By.xpath(xPath);
    }

    private By checkoutButtonElement = By.id("checkout");
    private By finishButtonElement = By.id("finish");
    private By totalPriceElement = By.xpath("//*[contains(@class,'summary_total_label')]");
    private By taxElement = By.xpath("//*[@class ='summary_tax_label']");

    public BasketPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
    }

    @Override
    public void openPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @Step("Check the product title")
    public List<String> getProductsTitle() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productTitleElement));
        return driver.findElements(productTitleElement).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Step("Add product to the basket")
    public void removeProductFromTheBasket(String productName) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(removeProductFromTheBasketElement(productName)));
        wait.until(ExpectedConditions.presenceOfElementLocated(removeProductFromTheBasketElement(productName))).click();
    }

    @Step("Is '{0}' Product shown?")
    public boolean isProductShown(String productName) {
        try {
            return driver.findElement(productName(productName)).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    @Step("Get the {0} product price")
    public double getProductPrice(String productName) {
        return Double.parseDouble(driver.findElement(itemPriceElement(productName)).getText().replace("$", StringUtils.EMPTY));
    }

    @Step("Get the total price")
    public double getTotalPrice() {
        return Double.parseDouble(driver.findElement(totalPriceElement).getText().replace("Total: $", StringUtils.EMPTY));
    }

    @Step("Get the tax price")
    public double getTax() {
        return Double.parseDouble(driver.findElement(taxElement).getText().replace("Tax: $", StringUtils.EMPTY));
    }

    @Step("Click on checkout button")
    public void clickOnCheckoutButton() {
        driver.findElement(checkoutButtonElement).click();
    }

    @Step("Click on finish button")
    public void clickOnFinishButton() {
        driver.findElement(finishButtonElement).click();
    }
}
