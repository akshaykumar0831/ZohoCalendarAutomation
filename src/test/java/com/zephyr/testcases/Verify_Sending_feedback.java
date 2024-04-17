package com.zephyr.testcases;

import com.zephyr.framework.Pages.*;
import com.zephyr.framework.base.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Verify_Sending_feedback extends TestBase {

    CalenderPage calenderPage;
    LoginPage loginPage;
    FeedBackPage feedBackPage;

    public Verify_Sending_feedback()
    {
        super();
    }


    @BeforeMethod
    public void setup() throws Exception {
        // Execute Verify_TeamFunctionality first
        initialization();
        calenderPage = new CalenderPage();
        loginPage =new LoginPage();
        feedBackPage=new FeedBackPage();
        loginPage.clickOn_SignIn_HomePage();
        loginPage.Enter_UserName_Password();
        calenderPage.clickOn_feedback_Calender();

    }


    @Test
    public void Verify_CreateNewEvent() throws Exception {

        feedBackPage.send_feedback();

    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
