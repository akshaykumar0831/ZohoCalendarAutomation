package com.zephyr.framework.Pages;

import com.zephyr.framework.Utils.TestUtils;
import com.zephyr.framework.base.TestBase;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class ZohoMail extends TestBase {


    public static Logger logger = Logger.getLogger(LoginPage.class);


    public ZohoMail()
    {
        PageFactory.initElements(driver,this);
    }

    public void validate_zohomailPage() throws InterruptedException {
        Set<String> windowHandles = driver.getWindowHandles();
        // Switch to the new tab (assuming it's the only new window opened)
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
        }
        Thread.sleep(5000);
        String expectedUrl = "https://mail.zoho.in/zm/#mail/folder/inbox"; // Change this to the expected URL
        String currentUrl = driver.getCurrentUrl();

        System.out.println("expectedUrl : " +expectedUrl );
        System.out.println("currentUrl : " +currentUrl );

        if (currentUrl.equals(expectedUrl)) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

}
