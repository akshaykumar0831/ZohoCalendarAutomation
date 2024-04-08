package com.zephyr.testcases;

import com.zephyr.framework.Pages.*;
import com.zephyr.framework.base.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Verify_OpeningZohomail extends TestBase {

    CalenderPage calenderPage;
    LoginPage loginPage;
    NewEventPage newEventPage;
    ProfilePage profilePage;
    ZohoMail zohoMail;

    public static Logger logger = Logger.getLogger(Verify_UpdatingEvent_NewDate.class);

    public Verify_OpeningZohomail()
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
        calenderPage.clickOn_menuItem_Calender();
        calenderPage.clickOn_mail_Calender();
        zohoMail=new ZohoMail();

    }

    @Test(priority = 1)
    public void Verify_ProfilePage_update() throws Exception {
        zohoMail.validate_zohomailPage();
    }

    @Test(priority = 2)
    public void Verify_ProfilePage_update_inbox() throws Exception {
        zohoMail.validate_zohomailPage();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
