package api_learning;

import contexts.AppContexts;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.rmi.Naming;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HandleHybridContext implements AppContexts {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
try {
    //Click on login label
    MobileElement webviewLabelElem = androidDriver.findElementByAccessibilityId("Webview");
    webviewLabelElem.click();
    WebDriverWait wait = new WebDriverWait(androidDriver,10L);
    wait.until(moreThanOneContext(androidDriver));
    androidDriver.getContextHandles().forEach(context -> {
        System.out.println(context);
    });
    androidDriver.context(WEB);
    WebElement navToggleBtn = androidDriver.findElementByCssSelector(".navbar__toggle");
    navToggleBtn.click();

    List<MobileElement> menuItems = androidDriver.findElementsByCssSelector(".menu__list-item a");
    List<MenuItem> menuItemList = new ArrayList<>();
    if (menuItems.isEmpty())
        throw new RuntimeException("[ERR] Menu items is empty!");
    menuItems.forEach(menuItem -> {
        String itemText = menuItem.getText();
        String itemHyperLink = menuItem.getAttribute("href");

        if (StringUtils.isEmpty(itemText))
            menuItemList.add(new MenuItem("Github", itemHyperLink));
        else
            menuItemList.add(new MenuItem(itemText, itemHyperLink));
    });
    menuItemList.forEach(menuItemData ->{
        System.out.println(menuItemData);
    });
    // Switch back to NATIVE context
    androidDriver.context(NATIVE);
    androidDriver.findElementByAccessibilityId("Login").click();

    //
    androidDriver.runAppInBackground(Duration.ofSeconds(3));

}catch (Exception e){
    e.printStackTrace();
}finally {
    androidDriver.quit();
    DriverFactory.stopAppiumServer();
}
    }
    private static final ExpectedCondition<Boolean> moreThanOneContext(AppiumDriver<MobileElement> appiumDriver){
        return driver -> appiumDriver.getContextHandles().size()>1;
    }

    public static class MenuItem {
        private String text;
        private String hyperLink;
        public MenuItem(String text, String hyperLink){
            this.text = text;
            this.hyperLink = hyperLink;
        }

        @Override
        public String toString() {
            return "MenuItem{" +
                    "text='" + text + '\'' +
                    ", hyperLink='" + hyperLink + '\'' +
                    '}';
        }
    }

}
