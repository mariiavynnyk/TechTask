package org.test.framework.pages.login;

import io.qameta.allure.Step;
import org.test.framework.pages.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends AbstractPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By passwordElement = By.id("password");
    private By userNameElement = By.id("user-name");
    private By loginButtonElement = By.id("login-button");

    private By errorElement = By.xpath("//*[contains(text(),'Invalid email')]");
    private By navBarAccountElement = By.id("navbarAccount");
    private By goToUserProfileElement = By.cssSelector("[aria-label='Go to user profile'] span");
    private String emptyString = "          ";
    private By logOutElement = By.xpath("//*[@id='navbarLogoutButton']//span");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
    }

    @Override
    public void openPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @Step("Enter username & password")
    public void logIn(String email, String password) {
        wait.until(ExpectedConditions.presenceOfElementLocated(userNameElement));
        driver.findElement(userNameElement).sendKeys(email);
        driver.findElement(passwordElement).sendKeys(password);
        clickOnLoginButton();
    }

    @Step("Click on login button")
    public void clickOnLoginButton() {
        driver.findElement(loginButtonElement).click();
    }

    @Step("User log out")
    public void logOut() {
        wait.until(ExpectedConditions.presenceOfElementLocated(logOutElement));
        driver.findElement(logOutElement).click();
    }
}
