package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class FormHandling {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
try {
    //Click on login label
    MobileElement loginLabelElm = androidDriver.findElementByAccessibilityId("Login");
    loginLabelElm.click();

    //Find elements
    MobileElement usernameE = androidDriver.findElementByAccessibilityId("input-email");
    MobileElement passwordE = androidDriver.findElementByAccessibilityId("input-password");
    MobileElement loginbtnE = androidDriver.findElementByAccessibilityId("button-LOGIN");

    //Input username
    usernameE.sendKeys("kylenguyen");

    //Input password
    passwordE.sendKeys("123456");

    //Click login btn
    loginbtnE.click();

    //Clear
    usernameE.clear();
    usernameE.sendKeys("kylenguyen1311@gmail.com");
    loginbtnE.click();

}catch (Exception e){
    e.printStackTrace();
}finally {
    androidDriver.quit();
}
    }
}
