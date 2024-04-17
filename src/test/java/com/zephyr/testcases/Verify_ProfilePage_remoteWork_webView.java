package com.zephyr.testcases;

import com.zephyr.framework.Pages.CalenderPage;
import com.zephyr.framework.Pages.LoginPage;
import com.zephyr.framework.Pages.NewEventPage;
import com.zephyr.framework.Pages.ProfilePage;
import com.zephyr.framework.base.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Verify_ProfilePage_remoteWork_webView  extends TestBase {

    CalenderPage calenderPage;
    LoginPage loginPage;
    ProfilePage profilePage;


    public Verify_ProfilePage_remoteWork_webView()
    {
        super();
    }


    @BeforeMethod
    public void setup() throws Exception {
        // Execute Verify_TeamFunctionality first
        initialization();
        calenderPage = new CalenderPage();
        loginPage =new LoginPage();
        profilePage=new ProfilePage();
        loginPage.clickOn_SignIn_HomePage();
        loginPage.Enter_UserName_Password();
        calenderPage.clickOn_Profile_Calender();

    }


    @Test(priority = 1)
    public void Verify_remoteWork_webView() throws Exception {

        profilePage.check_profile_WebPages();
    }


    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
