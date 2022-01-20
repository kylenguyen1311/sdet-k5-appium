package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.time.Duration;

public class HandleMultipleApps {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
        try {
            //Click on login label
            MobileElement loginLabelElem = androidDriver.findElementByAccessibilityId("Login");
            loginLabelElem.click();

        // //*[@text="WLAN"]
        //

        // Go to login form -> Input correct credentials then login
            MobileElement usernameE = androidDriver.findElementByAccessibilityId("input-email");
            MobileElement passwordE = androidDriver.findElementByAccessibilityId("input-password");
            MobileElement loginbtnE = androidDriver.findElementByAccessibilityId("button-LOGIN");
            usernameE.sendKeys("kylenguyen1311@gmail.com");
            passwordE.sendKeys("12345678");
            loginbtnE.click();

        // Put app into background
            androidDriver.runAppInBackground(Duration.ofSeconds(3));

            // Open Settings -> Handle Wifi
            androidDriver.activateApp("com.android.settings");
            androidDriver.findElementByXPath("//*[@text='WLAN']").click();
            MobileElement wifiSwitchBtnElem = androidDriver.findElement(By.id("android:id/checkbox"));
            MobileElement wifiOffContent = androidDriver.findElement(By.id("android:id/summary"));
            Boolean isWifiOn = !wifiOffContent.getText().equals("To see available networks, turn on WLAN");
            wifiSwitchBtnElem.click();

            if (isWifiOn){
                wifiSwitchBtnElem.click();
            } else {
                wifiSwitchBtnElem.click();
            }

            // Open the test app again
            androidDriver.activateApp("com.wdiodemoapp");
            androidDriver.findElementByXPath("//*[@text='OK']").click();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            androidDriver.quit();
            DriverFactory.stopAppiumServer();
        }
    }
}
