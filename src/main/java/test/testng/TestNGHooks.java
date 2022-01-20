package test.testng;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TestNGHooks {
    @Test(priority = 3)
    public void testA(){
        System.out.println("testA");
        String actualValue = "testA";
        String exValue = "testA ";
        // Hard assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualValue,exValue,"[ERR] msg1");
        softAssert.assertEquals(true,true,"[ERR] msg1");
        softAssert.assertEquals(true,true,"[ERR] msg1");
        softAssert.assertTrue(actualValue.equals(exValue), "[ERR] msg2");

        softAssert.assertAll();

//        List<MobileElement> list = appiumDriver.findElements(MobileBy.id("...."));
//        if (list.isEmpty()) Assert.fail("[ERR] List of ... is empty");
//        for(MobileElement mobileElement : list){
//            // verification points
//        }
//        Assert.fail("Custom err msg");
    }
    @Test(priority = 1)
    public void testB(){
        System.out.println("testB");
    }
    @Test(priority = 2, dependsOnMethods = {"testA"})
    public void testC(){
        System.out.println("testC");
    }
}
