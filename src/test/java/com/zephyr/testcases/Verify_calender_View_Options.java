package com.zephyr.testcases;

import com.zephyr.framework.Pages.CalenderPage;
import com.zephyr.framework.Pages.LoginPage;
import com.zephyr.framework.Pages.NewEventPage;
import com.zephyr.framework.base.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Verify_calender_View_Options extends TestBase {

    CalenderPage calenderPage;
    LoginPage loginPage;


    public Verify_calender_View_Options()
    {
        super();
    }


    @BeforeMethod
    public void setup() throws Exception {
        // Execute Verify_TeamFunctionality first
        initialization();
        calenderPage = new CalenderPage();
        loginPage =new LoginPage();
        loginPage.clickOn_SignIn_HomePage();
        loginPage.Enter_UserName_Password();

    }


    @Test(priority = 1)
    public void Verify_Days_CalenderView() throws Exception {

        calenderPage.validate_days_fromCalender("Day");
    }
    @Test(priority = 2)
    public void Verify_Week_CalenderView() throws Exception {

        calenderPage.validate_days_fromCalender("Week");


    }
    @Test(priority = 3)
    public void Verify_Month_CalenderView() throws Exception {

        calenderPage.validate_days_fromCalender("Month");

    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
