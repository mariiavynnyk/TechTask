package org.test.firstTask.framework.pages.checkout;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.test.firstTask.framework.pages.AbstractPage;

import java.time.Duration;

public class CheckoutPage extends AbstractPage {
    private final WebDriver driver;
    private final By continueButtonElement = By.id("continue");
    private final By firstNameElement = By.id("first-name");
    private final By lastNameElement = By.id("last-name");
    private final By postalCodeElement = By.id("postal-code");
    private final By completeOrderTextElement = By.xpath("//span[contains(text(), 'Checkout: Complete!')]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
    }

    @Override
    public void openPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @Step("Click on continue button")
    public void clickOnContinueButton() {
        driver.findElement(continueButtonElement).click();
    }

    @Step("Fill first name {0}, last name {1} and zip {2} fields.")
    public void fillInformationFieldsIfNeededAndContinue(String firstName, String lastName, int zipCode) {
        if (driver.findElement(firstNameElement).isDisplayed()) {
            driver.findElement(firstNameElement).sendKeys(firstName);
            driver.findElement(lastNameElement).sendKeys(lastName);
            driver.findElement(postalCodeElement).sendKeys(String.valueOf(zipCode));
            clickOnContinueButton();
        }
    }

    @Step("Get Success message")
    public boolean isSuccessCheckoutMessageShown() {
        try {
            return driver.findElement(completeOrderTextElement).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
