package com.zephyr.testcases;

import com.zephyr.framework.Pages.CalenderPage;
import com.zephyr.framework.Pages.LoginPage;
import com.zephyr.framework.Pages.NewEventPage;
import com.zephyr.framework.base.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Verify_Searching_CreatedEvent extends TestBase {

    CalenderPage calenderPage;
    LoginPage loginPage;
    NewEventPage newEventPage;

    public static Logger logger = Logger.getLogger(Verify_UpdatingEvent_NewDate.class);

    public Verify_Searching_CreatedEvent()
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
    public void Verify_SearchEvent() throws Exception {
        calenderPage.searchEvent_Calender(prop.getProperty("EventTitle"));
        newEventPage.isEventDisplayed_afterSearch(prop.getProperty("EventTitle"));
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
