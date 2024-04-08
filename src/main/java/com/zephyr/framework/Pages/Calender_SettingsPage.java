package com.zephyr.framework.Pages;

import com.zephyr.framework.Utils.TestUtils;
import com.zephyr.framework.base.TestBase;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calender_SettingsPage extends TestBase {


    @FindBy(xpath="//span[contains(text(),'Time Zone')][@class='zmsettings-nav-action-primary-name']")
    WebElement TimeZone_Button_Settings;

    @FindBy(xpath="(//input[@type='checkbox'][@value='enable'])[2]")
    WebElement checkbox_timeZone;

    @FindBy(xpath="//*[contains(text(),'Secondary Time Zone')]")
    WebElement Secondary_TimeZone_header_Settings;

    @FindBy(xpath="//li[@class='zclevent__timezone__list__item ']")
    WebElement TimeZone_Options_FullList1;

    @FindAll({@FindBy(xpath="//li[@class='zclevent__timezone__list__item ']")})
    List<WebElement> TimeZone_Options_FullList;

    @FindBy(xpath="//button[@data-testid='set-tz-secondary-btn']")
    WebElement Change_TimeZone_link;

    @FindBy(xpath="//*[@data-testid='set-tz-secondary-selected']")
    WebElement already_selected_TimeZone;

    @FindBy(xpath="//input[@data-testid='set-tz-search']")
    WebElement searchBox_TimeZone;

    @FindBy(xpath="//*[@data-testid='set-tz-secondary-selected']")
    WebElement newTimezone_calender;

    @FindBy(xpath="//*[@data-testid='set-tz-done-btn']")
    WebElement Done_Button_calender;

    @FindBy(xpath="//*[@class='zmdialog__close__3v915h zmdialog__close--outside__1g08kc1']")
    WebElement close_button_calender;

    public Calender_SettingsPage()
    {

        PageFactory.initElements(driver,this);
    }


    public void clickOn_TimeZone()
    {
        TestUtils.clickElement(TimeZone_Button_Settings);
    }

    public void enable_secondary_TimeZone() throws Exception {

        String existingTimezone = prop.getProperty("existingTimezone");
        String newTimezone = prop.getProperty("NewTimeZone");

// Check if the checkbox is already selected
        TestUtils.explicitWait_waitTillElementIsClickable(Secondary_TimeZone_header_Settings);
        String checkboxStatus = checkbox_timeZone.getAttribute("aria-checked");
        System.out.println("Checkbox status is: " + checkboxStatus);

        if (checkboxStatus.equalsIgnoreCase("true")) {
            System.out.println("Timezone is already selected");
            if (already_selected_TimeZone.isDisplayed()) {
                // If the existing timezone is different from the new timezone, proceed to update
                if (!already_selected_TimeZone.getText().equalsIgnoreCase(newTimezone)) {
                    System.out.println("Existing timezone is different from the new timezone");

                    // Perform steps to update timezone
                    updateTimeZone(newTimezone);
                } else {
                    updateTimeZone(existingTimezone);
                }
            }
        } else {
            // If the checkbox is not selected, select it and update timezone
            System.out.println("Checkbox is not selected, selecting it and updating timezone");
            checkbox_timeZone.click();
            TestUtils.clickElement(checkbox_timeZone);
            updateTimeZone(newTimezone);
        }

    }
    private void updateTimeZone(String newTimezone) throws Exception {
        TestUtils.explicitWait_waitTillElementIsClickable(Change_TimeZone_link);
        TestUtils.clickElement(Change_TimeZone_link);
        TestUtils.sendKeys(searchBox_TimeZone, newTimezone);

        // Extract abbreviation from timezone
        Pattern pattern = Pattern.compile("\\((\\w+)\\)");
        Matcher matcher = pattern.matcher(newTimezone);
        String abbreviation = null;
        if (matcher.find()) {
            abbreviation = matcher.group(1);
            System.out.println("Abbreviation: " + abbreviation);
        }

        // Click on the timezone option
        WebElement timeZoneOption = driver.findElement(By.xpath("//*[@rel='" + abbreviation + "']"));
        TestUtils.explicitWait_waitTillElementIsClickable(timeZoneOption);
        timeZoneOption.click();
        Thread.sleep(5000);

        TestUtils.clickElement(Done_Button_calender);
        TestUtils.explicitWait_VisibilityOfElement(newTimezone_calender);
        Thread.sleep(5000);
        System.out.println("new timezone visible after selection : " + newTimezone_calender.getText());
        System.out.println("new timezone from config selection   : " + newTimezone);

        // Assertion to verify the new timezone
        Assert.assertEquals(newTimezone_calender.getText().trim(),(newTimezone.trim()));
    }


}
