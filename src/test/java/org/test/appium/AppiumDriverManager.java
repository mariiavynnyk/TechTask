package org.test.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.*;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumDriverManager {
    public static AppiumDriver<MobileElement> getDriver() {
        // Set up Appium driver with desired capabilities for iOS or Android
        return null;
    }

    public static DesiredCapabilities iosNativeAppCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        caps.setCapability(IOSMobileCapabilityType.SHOW_XCODE_LOG, false);
        caps.setCapability(MobileCapabilityType.LANGUAGE, "EN");
        caps.setCapability(IOSMobileCapabilityType.CONNECT_HARDWARE_KEYBOARD, false);
        return caps;
    }

    private static DesiredCapabilities androidNativeAppCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");
        return caps;
    }
}
