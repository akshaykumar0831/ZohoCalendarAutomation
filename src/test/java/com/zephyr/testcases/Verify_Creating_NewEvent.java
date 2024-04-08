package com.zephyr.testcases;

import com.zephyr.framework.Pages.CalenderPage;
import com.zephyr.framework.Pages.LoginPage;
import com.zephyr.framework.Pages.NewEventPage;
import com.zephyr.framework.Utils.TestUtils;
import com.zephyr.framework.base.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;


public class Verify_Creating_NewEvent extends TestBase {

    CalenderPage calenderPage;
    LoginPage loginPage;
    NewEventPage newEventPage;

    public Verify_Creating_NewEvent()
    {
        super();
    }


    @BeforeMethod
    public void setup() throws Exception {
        // Execute Verify_TeamFunctionality first
        initialization();
        calenderPage = new CalenderPage();
        loginPage =new LoginPage();
        newEventPage=new NewEventPage();
        loginPage.clickOn_SignIn_HomePage();
        loginPage.Enter_UserName_Password();

    }


    @Test
    public void Verify_CreateNewEvent() throws Exception {

        calenderPage.clickOn_NewEvent();
        newEventPage.fillTheDetails_ToCreateAnewEvent();
        newEventPage.isEventDisplayed(prop.getProperty("EventTitle"));

    }

    @AfterMethod
    public void tearDown() throws IOException {
        TestUtils.takeScreenshotAtEndOfTest();
        driver.close();
        driver.quit();
    }

}
