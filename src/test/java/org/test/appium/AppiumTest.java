package org.test.appium;

//What are the mobile application types you are familiar with?
// Answer: I'm familiar with Native App.
// These are applications built specifically for a particular platform (e.g., iOS or Android)
// using the platform's native programming languages and APIs.
// They offer high performance and full access to device features but require separate development for each platform.

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

import static org.test.appium.AppConfiguration.ANDROID_APP_PATH;
import static org.test.appium.AppConfiguration.iOS_APP_PATH;

public abstract class AppiumTest {
    protected AppiumDriver<MobileElement> driver;

    @BeforeMethod
    public void setUp() {
        driver = AppiumDriverManager.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
//            driver.quit();
        }
    }

    private static WebDriver startAndroid(DesiredCapabilities capabilities) {
        AndroidDriver driver;
        String APPIUM_URL = System.getProperty("appiumURL");
        if (APPIUM_URL == null) {
            APPIUM_URL = ANDROID_APP_PATH;
        }
        try {
            driver = new AndroidDriver(new URL(APPIUM_URL), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }

    private static WebDriver startIos(DesiredCapabilities capabilities) {
        IOSDriver driver;
        String APPIUM_URL = System.getProperty("appiumURL");

        if (APPIUM_URL == null) {
            APPIUM_URL = iOS_APP_PATH;
        }
        try {
            driver = new IOSDriver(new URL(APPIUM_URL), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }
}
