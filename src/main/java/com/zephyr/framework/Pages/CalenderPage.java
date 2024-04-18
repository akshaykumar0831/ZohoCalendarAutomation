package com.zephyr.framework.Pages;

import com.zephyr.framework.Utils.TestUtils;
import com.zephyr.framework.base.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class CalenderPage extends TestBase {


    @FindBy(xpath="//*[contains(text(),'New Event')]")
    WebElement NewEvent_Button;

    @FindBy(xpath="//*[@placeholder='Search Events']")
    WebElement Calender_search;

    @FindAll({@FindBy(xpath="//li[@role='menuitem']/span")})
    List<WebElement> DropDown_Options_calenderDaysView;

    @FindAll({@FindBy(xpath="//*[@data-testid='dayview-date']/ul/li")})
    List<WebElement> All_Days_calenderDaysView;

    @FindAll({@FindBy(xpath="//*[@data-testid='grid-month-crnt-date'] | //li[@class='' and @data-testid='' and @sel='true']")})
    List<WebElement> Month_AllDays_calenderDaysView;

    @FindAll({@FindBy(xpath="//*[@data-testid='dayview-date']/ul/li")})
    List<WebElement> Week_AllDays_calenderDaysView;

    @FindBy(xpath="//*[@data-testid='cal-selected-view']")
    WebElement DropDown_calenderDaysView;

    @FindBy(xpath="//*[@data-tooltip='Edit']")
    WebElement Calender_EditButton;

    @FindBy(xpath="//*[@data-tooltip='My Profile']")
    WebElement profile_calender;

    @FindBy(xpath="//*[@href='//accounts.zoho.com']")
    WebElement MyAccount_calender;

    @FindBy(xpath="//*[@data-testid='ZiconComponent']")
    WebElement menuItem_calender;

    @FindBy(xpath="//*[@data-appname='mail']")
    WebElement mailItem_calender;

    @FindBy(xpath="//*[@data-tooltip='Settings']")
    WebElement settings_calender;

    @FindBy(xpath="//*[@data-tooltip='Send Feedback']")
    WebElement feedback_option_calender;






    public CalenderPage()
    {

        PageFactory.initElements(driver,this);
    }
    public static Logger logger = Logger.getLogger(CalenderPage.class);
    public void clickOn_NewEvent()
    {
        TestUtils.clickElement(NewEvent_Button);
    }

    public void searchEvent_Calender(String event) throws Exception {
        TestUtils.waitForSpecificTime();
        TestUtils.clickElement(Calender_search);
        TestUtils.sendKeys(Calender_search,event);
        Calender_search.sendKeys(Keys.ENTER);
    }

    public void clickOn_CreatedEvent_Calender(String option) throws InterruptedException {
        driver.findElement(By.xpath("//*[@class='zcl_bld6 zcl_txbrk'][contains(text(),'" + option + "')]")).click();
    }

    public void edit_ExistingEvent_Calender() throws InterruptedException {
        TestUtils.clickElement(Calender_EditButton);
    }

    public void clickOn_Profile_Calender() throws Exception {
        TestUtils.explicitWait_waitTillElementIsClickable(profile_calender);
        TestUtils.clickElement(profile_calender);
    }
    public void clickOn_myAccount_Calender() throws Exception {
        TestUtils.explicitWait_VisibilityOfElement(MyAccount_calender);
        TestUtils.clickElement(MyAccount_calender);
    }

    public void clickOn_menuItem_Calender() throws InterruptedException {
        TestUtils.clickElement(menuItem_calender);
    }

    public void clickOn_mail_Calender() throws InterruptedException {
        TestUtils.clickElement(mailItem_calender);
    }
    public void clickOn_settings_Calender() throws Exception {
        TestUtils.explicitWait_VisibilityOfElement(settings_calender);
        TestUtils.explicitWait_waitTillElementIsClickable(settings_calender);
        //settings_calender.click();
        TestUtils.clickElement(settings_calender);
    }

    public void clickOn_feedback_Calender() throws Exception {
        WebElement element = driver.findElement(By.cssSelector("li.jszmChtBndFdbck"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }
    public void validate_days_fromCalender(String option) throws Exception {
        int n=1;
        while (n <= 3) {
            TestUtils.explicitWait_waitTillElementIsClickable(NewEvent_Button);
            TestUtils.clickElement(DropDown_calenderDaysView);
            validate_forEach_calenderOption(option);
            if  (n <= 3) {
                break;
            }

        }

    }

    private void validate_forEach_calenderOption(String option) throws Exception {

// Validate the number of days based on the selected option
        switch (option) {
            case "Month":
                select_options_fromDropdown("Month");
                int numberOfDays = Month_AllDays_calenderDaysView.size();
                System.out.println("Number of days visible: " + numberOfDays);
                Assert.assertEquals(numberOfDays, 30, "Number of days in a month is incorrect");
                break;
            case "Week":
                select_options_fromDropdown("Week");
                int numberOfDays_week = Week_AllDays_calenderDaysView.size();
                System.out.println("Number of days visible: " + numberOfDays_week);
                Assert.assertEquals(numberOfDays_week, 7, "Number of days in a week is incorrect");
                break;
            case "Day":
                select_options_fromDropdown("Day");
                int numberOfDays_Day = All_Days_calenderDaysView.size();
                System.out.println("Number of days visible: " + numberOfDays_Day);
                Assert.assertEquals(numberOfDays_Day, 1, "Number of days in a day is incorrect");
                break;
            default:
                System.out.println("Invalid option selected");
        }
    }
    private void select_options_fromDropdown(String option) throws Exception {
        TestUtils.waitForSpecificTime();
        for(WebElement element : DropDown_Options_calenderDaysView)
        {
            try {
                if (element.getText().equalsIgnoreCase(option)) {
                    element.click();
                    break; // Exit loop once the element is clicked
                }
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException occurred. Retrying...");
            }
        }
    }


}
