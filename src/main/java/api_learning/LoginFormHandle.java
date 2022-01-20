package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LoginFormHandle {
    public static void main(String[] args) {

        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
        try {
            //Click on login label
            MobileElement loginLabelElem = androidDriver.findElementByAccessibilityId("Login");
            loginLabelElem.click();

            final int EMAIL_FIELD_INDEX = 0;
            final int PASSWORD_FIELD_INDEX = 1;
            List<MobileElement> loginFormInputElems = androidDriver.findElements(By.xpath("//android.widget.EditText"));
            if (loginFormInputElems.isEmpty()){
                throw new RuntimeException("Login form elems can't be found!");
            }
            else {
                loginFormInputElems.get(EMAIL_FIELD_INDEX).sendKeys("new@sth.com");
                loginFormInputElems.get(PASSWORD_FIELD_INDEX).sendKeys("12345678");

            }

            //Find elements
            MobileElement usernameE = androidDriver.findElementByAccessibilityId("input-email");
            MobileElement passwordE = androidDriver.findElementByAccessibilityId("input-password");
            MobileElement loginbtnE = androidDriver.findElementByAccessibilityId("button-LOGIN");

            //Input username
            usernameE.sendKeys("kylenguyen1311@gmail.com");

            //Input password
            passwordE.sendKeys("12345678");

            //Click login btnß
            loginbtnE.click();

            WebDriverWait wait = new WebDriverWait(androidDriver, 10L);
            wait.until(ExpectedConditions.visibilityOf(androidDriver.findElement(By.id("android:id/alertTitle"))));
            MobileElement loginDialogTitle = androidDriver.findElement(By.id("android:id/alertTitle"));
            System.out.println("Login Title: " + loginDialogTitle.getText());

            MobileElement loginDialogXpathElem = androidDriver.findElementByXPath("//*[@resource-id='android:id/topPanel']//*[@resource-id='android:id/alertTitle']");
            System.out.println(loginDialogXpathElem.getText());

            //Find hidden elements -> add to a List
            List<MobileElement> badSelectorElem = androidDriver.findElementsByXPath("abc");
            if(!badSelectorElem.isEmpty()){
                throw new RuntimeException("wrong element still displayed!");
            }
            badSelectorElem.get(0).getText();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            androidDriver.quit();
        }
    }
}
