package test.authentication;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.gson.LoginCred;
import test_flows.authentication.LoginFlow;
import utils.data.DataObjectBuilder;

public class LoginTest {

    @Test(dataProvider = "invalidLoginData")
    public void loginWithInvalidCreds(LoginCred loginCredData) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        LoginFlow loginFlow = new LoginFlow(androidDriver);
        loginFlow.setData(loginCredData).login().verifyLogin();

        }
    @Test
    public void loginWithValidCreds() {
        String jsonLoc = "/src/main/resources/test-data/ValidLoginCred.json";
        LoginCred loginCredData = DataObjectBuilder.buildDataObject(jsonLoc, LoginCred[].class)[0];

        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        LoginFlow loginFlow = new LoginFlow(androidDriver);
        loginFlow.setData(loginCredData).login().verifyLogin();
    }
    @DataProvider
    public LoginCred[] invalidLoginData(){
        String jsonLoc = "/src/main/resources/test-data/LoginCred.json";
        return DataObjectBuilder.buildDataObject(jsonLoc, LoginCred[].class);

    }
}
