package test;

import driver.DriverFactoryEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class BaseTest {

    private DriverFactoryEx driverFactoryEx;
    private AppiumDriver<MobileElement> appiumDriver;

    private final List<DriverFactoryEx> driverThreadPool = Collections.synchronizedList(new ArrayList<>());  // Not a thread-safe
    private ThreadLocal<DriverFactoryEx> driverThread;

    private String udid;
    private String systemPort;

    @BeforeTest(alwaysRun = true)
    @Parameters({"udid", "systemPort"})
    public void beforeTest(String udid, String systemPort){
        this.udid = udid;
        this.systemPort = systemPort;
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactoryEx driverThread = new DriverFactoryEx();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(){
        driverThread.get().quitAppiumSession();
    }

    protected AppiumDriver<MobileElement> getAndroidDriver(){
        if(appiumDriver == null){
            appiumDriver = driverThread.get().getAndroidDriver(udid, systemPort);
        }
        return appiumDriver;
    }
}
