package com.zephyr.testcases;

import com.zephyr.framework.Pages.CalenderPage;
import com.zephyr.framework.Pages.Calender_SettingsPage;
import com.zephyr.framework.Pages.LoginPage;
import com.zephyr.framework.Pages.NewEventPage;
import com.zephyr.framework.base.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Verify_CalenderTimeZone_Update extends TestBase {

    CalenderPage calenderPage;
    Calender_SettingsPage calender_SettingsPage;
    LoginPage loginPage;
    NewEventPage newEventPage;

    public Verify_CalenderTimeZone_Update()
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
        calender_SettingsPage=new Calender_SettingsPage();
        loginPage.clickOn_SignIn_HomePage();
        loginPage.Enter_UserName_Password();
        calenderPage.clickOn_settings_Calender();

    }


    @Test
    public void Verify_CreateNewEvent() throws Exception {

        calender_SettingsPage.clickOn_TimeZone();
        calender_SettingsPage.enable_secondary_TimeZone();

    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}
