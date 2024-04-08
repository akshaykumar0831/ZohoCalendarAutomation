package com.zephyr.testcases;

import com.zephyr.framework.Pages.CalenderPage;
import com.zephyr.framework.Pages.LoginPage;
import com.zephyr.framework.Pages.NewEventPage;
import com.zephyr.framework.Utils.TestUtils;
import com.zephyr.framework.base.TestBase;

import org.apache.commons.compress.utils.TimeUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.bidi.log.LogEntry;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Verify_UpdatingEvent_NewDate extends TestBase {

    CalenderPage calenderPage;
    LoginPage loginPage;
    NewEventPage newEventPage;

    public static Logger logger = Logger.getLogger(Verify_UpdatingEvent_NewDate.class);

    public Verify_UpdatingEvent_NewDate()
    {
        super();
    }


    @BeforeMethod
    public void setup() throws Exception {
        // Execute Verify_TeamFunctionality first
        initialization();
        loginPage =new LoginPage();
        newEventPage=new NewEventPage();
        loginPage.clickOn_SignIn_HomePage();
        loginPage.Enter_UserName_Password();
        calenderPage = new CalenderPage();

    }

    @Test(priority = 1)
    public void Verify_UpdatingNewEvent_ToNewDate() throws Exception {
        calenderPage.searchEvent_Calender(prop.getProperty("EventTitle"));
        calenderPage.clickOn_CreatedEvent_Calender(prop.getProperty("EventTitle"));
        calenderPage.edit_ExistingEvent_Calender();
        newEventPage.update_ExistingEvent(prop.getProperty("updatedEventTitle"));
        newEventPage.isEventDisplayed(prop.getProperty("updatedEventTitle"));
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
