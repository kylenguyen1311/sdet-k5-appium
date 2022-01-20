package test.testng;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginFormTest {

    @Test
    public void loginWithValidCred() {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
        try{
            LoginPage loginPage = new LoginPage(androidDriver);
            loginPage.bottomNavigationComponent().clickOnLoginLabel();
            // Method chaining
            loginPage
                    .inputUsername("kyle@meo.com")
                    .inputPassword("kyle@meo.com")
                    .clickOnLoginBtn();
        }
        finally {
            androidDriver.quit();
        }
        }

    @Test
    public void testSth() {
        System.out.println("Test Sth");
    }
}
