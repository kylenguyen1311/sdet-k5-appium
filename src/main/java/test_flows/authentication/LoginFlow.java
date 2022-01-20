package test_flows.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.LoginPage;
import test.gson.LoginCred;

public class LoginFlow {

    // Class vs Object | States/Attributes

    // IMPORTANT: Clarify Object States/Attributes
    private AppiumDriver<MobileElement> appiumDriver;
    private LoginCred loginCredData;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver){
        this.appiumDriver = appiumDriver;
    }
    public LoginFlow setData(LoginCred loginCredData){
        this.loginCredData = loginCredData;
        return this;

    }

    public LoginFlow login(){
        if(loginCredData == null)
            throw new RuntimeException("Please call setData method first");
        LoginPage loginPage = new LoginPage(appiumDriver);
        loginPage.bottomNavigationComponent().clickOnLoginLabel();
        loginPage
                .inputUsername(loginCredData.getUsername())
                .inputPassword(loginCredData.getPassword())
                .clickOnLoginBtn();
        return this;
    }

    public void verifyLogin(){
        boolean isEmailInvalid = isEmailInvalid(loginCredData.getUsername());
        boolean isPasswordInvalid = isPasswordInvalid(loginCredData.getUsername());
        if(isEmailInvalid) verifyInvalidEmailFormat();
        if(isPasswordInvalid) verifyInvalidPasswordFormat();
        if(!isEmailInvalid && !isPasswordInvalid) verifyLoginSuccess();

    }

    private void verifyLoginSuccess() {
    }

    private void verifyInvalidPasswordFormat() {
    }

    private boolean isPasswordInvalid(String username) {
        return true;
    }

    private void verifyInvalidEmailFormat() {
    }

    private boolean isEmailInvalid(String username) {
        // String Regex email format
        return true;
    }
}
