package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavigationComponent;
import org.openqa.selenium.By;

public class LoginPage {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By usernameSelector = MobileBy.AccessibilityId("input-email");
    private static final By passwordSelector = MobileBy.AccessibilityId("input-password");
    private static final By loginSelector = MobileBy.AccessibilityId("button-LOGIN");

    public LoginPage(AppiumDriver<MobileElement> appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    // Return found element(s)
    private MobileElement usernameElem(){
        return appiumDriver.findElement(usernameSelector);
    }
    public LoginPage inputUsername(String username){
        appiumDriver.findElement(usernameSelector).sendKeys(username);
        return this;
    }
    public LoginPage inputPassword(String password){
        appiumDriver.findElement(passwordSelector).sendKeys(password);
        return this;
    }
    public void clickOnLoginBtn(){
        appiumDriver.findElement(loginSelector).click();
    }

    public BottomNavigationComponent bottomNavigationComponent(){
        return new BottomNavigationComponent(appiumDriver);
    }
}
