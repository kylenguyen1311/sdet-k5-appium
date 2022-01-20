package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SwipeToOpenNotification {
    public static void main(String[] args) throws InterruptedException {
        DriverFactory.startAppiumServer();

            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement formsLabel = androidDriver.findElementByAccessibilityId("Forms");
            formsLabel.click();

            //Get Mobile size
            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            //Calculate touch points
            int xStartPoint = (50 * screenWidth) / 100;
            int xEndPoint = xStartPoint;
            int yStartPoint = 0;
            int yEndPoint = (50 * screenWidth) / 100;

            // Convert to PointOptions - Coordinates
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);


            // Perform Actions
            // Swipe down to open notification
            TouchAction touchAction = new TouchAction(androidDriver);
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1L)))
                    .moveTo(endPoint)
                    .release()
                    .perform();
            Thread.sleep(2000);

            List<MobileElement> notificationElems = androidDriver.findElements(By.id("android:id/notification_main_column"));
            List<Notification> notifications = new ArrayList<>();

            // Functional Interface + lambda expression
            // Anonymous function | returnType methodName() {} |||| () -> {}
            notificationElems.forEach(notificationElem -> {
                String notificationTitle = notificationElem.findElement(By.id("android:id/title")).getText();
                By appNameByID = MobileBy.id("android:id/app_name_text");
                By textByID = MobileBy.id("android:id/text");

                List<MobileElement> appNameElems = androidDriver.findElements(appNameByID);
                List<MobileElement> textElems = androidDriver.findElements(textByID);
                List<MobileElement> notificationBodyElems =
                        !appNameElems.isEmpty() ? appNameElems :textElems ;
                String notificationBody =
                        notificationBodyElems.isEmpty()?null:notificationBodyElems.get(0).getText();
                notifications.add(new Notification(notificationTitle,notificationBody));
                System.out.println(notificationTitle);
            });

            // Verification
        notifications.forEach(notification -> {
            System.out.println(notification);

        });

            // Swipe up to close notification
            touchAction
                    .press(endPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1L)))
                    .moveTo(startPoint)
                    .release()
                    .perform();


    }
    public static class Notification {
        private final String title;
        private final String content;

        public Notification(String title, String content) {
            this.title = title;
            this.content = content;
        }
        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        @Override
        public String toString() {
            return "Notification{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
