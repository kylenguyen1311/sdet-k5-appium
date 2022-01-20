package models.components.global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class BottomNavigationComponent {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By loginLabelSelector = MobileBy.AccessibilityId("Login");
    private static final By swipeLabelSelector = MobileBy.AccessibilityId("Swipe");
    private static final By okBtnSelector = MobileBy.id("android:id/button1");
    public BottomNavigationComponent(AppiumDriver<MobileElement> appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public void clickOnLoginLabel(){
        appiumDriver.findElement(loginLabelSelector).click();
    }
    public void clickOnSwipeLabel(){
        appiumDriver.findElement(swipeLabelSelector).click();
    }
    public void clickOKBtn(){
        appiumDriver.findElement(okBtnSelector).click();
    }
}
