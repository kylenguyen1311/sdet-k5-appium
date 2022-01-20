package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class TakingScreenshot {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            androidDriver.findElementByAccessibilityId("Login").click();

            WebDriverWait wait = new WebDriverWait(androidDriver, 10L);
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    MobileBy.AccessibilityId("button-LOGIN")));

            //Taking screenshot | whole screen
            File base64ScreenshotData = androidDriver.getScreenshotAs(OutputType.FILE);
            String fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("LoginForm.png");
            FileUtils.copyFile(base64ScreenshotData, new File(fileLocation));

            // Taking screenshot on an element
            MobileElement loginBtnElem = androidDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));
            File base64LoginBtnData = loginBtnElem.getScreenshotAs(OutputType.FILE);
            String loginBtnFileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("LoginButton.png");
            FileUtils.copyFile(base64LoginBtnData, new File(loginBtnFileLocation));

            // Taking screenshot on an are
            MobileElement homeScreen = androidDriver.findElement(MobileBy.AccessibilityId("Login-screen"));
            File base64HomeScreenData = homeScreen.getScreenshotAs(OutputType.FILE);
            String homeScreenFileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("Homescreen.png");
            FileUtils.copyFile(base64HomeScreenData, new File(homeScreenFileLocation));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
