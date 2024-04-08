package com.zephyr.framework.Pages;

import com.zephyr.framework.Utils.TestUtils;
import com.zephyr.framework.base.TestBase;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewEventPage extends TestBase {


    @FindBy(xpath="//input[@data-testid='creeve-title-input']")
     WebElement Event_Title;

    @FindBy(xpath="//input[@data-testid='creeve-startdate']")
     WebElement Event_StartDate;

   @FindBy(xpath="//input[@data-testid='creeve-starttime']")
    WebElement Event_StartTime;

    @FindAll({@FindBy(xpath="//*[@role='option']")})
    List<WebElement> Event_StartTime_All_TimeOptions;


    @FindBy(xpath="//input[@data-testid='creeve-enddate']")
    WebElement Event_EndDate;

    @FindBy(xpath="//input[@data-testid='creeve-endtime']")
    WebElement Event_endTime;

    @FindBy(xpath="//input[@data-testid='create-ev-att']")
    WebElement Invite_mail;

    @FindBy(xpath="(//input[@value='Allow Forward'])[2]")
    WebElement allowForward_Checkbox;

    @FindBy(xpath="//input[@data-testid='create-ev-loc']")
    WebElement Enter_Location;

    @FindBy(xpath="//*[contains(text(),'Add conference')]")
    WebElement add_Conference;

    @FindBy(xpath="//*[contains(text(),'Zoho Meeting')]")
    WebElement zohoMeeting_add_Conference;

    @FindBy(xpath="//*[contains(text(),'Audio Meeting')]")
    WebElement selectAudio_add_Conference;

    @FindBy(xpath="//*[@placeholder='Enter url']")
    WebElement enter_Url;

    @FindBy(xpath="//*[@class='zmeditorcontent zcl_ce_ri_vw']/div")
    WebElement enter_description;

    @FindBy(xpath="//*[contains(text(),'Save')]")
    WebElement save_button;

    @FindBy(xpath="//*[contains(text(),'Attendees only')]")
    WebElement attendeeOption_Radi0Button;

    @FindBy(xpath="//*[contains(text(),'Confirm')]")
    WebElement ConfirmButton_Submit;

    @FindBy(xpath="//*[@data-testid='grid-event-dw'][contains(text(),'2nd event')]")
    WebElement Event_displayed_InCalender;

    @FindAll({@FindBy(xpath="//*[@data-testid='grid-event-dw']")})
    List<WebElement> ListOf_AllEvents;

    @FindBy(xpath="//*[@data-tooltip='Delete']")
    WebElement delete_Event_Button;

    @FindBy(xpath="//span[contains(text(),'Confirm')]/parent::*[1]")
    WebElement delete_confirm_Button;

    @FindBy(xpath="//*[contains(text(),'No events found')]")
    WebElement NoEvent_Message_AfterEventDelete;




    public static Logger logger = Logger.getLogger(NewEventPage.class);

    public NewEventPage()
    {

        PageFactory.initElements(driver,this);
    }

    public void fillTheDetails_ToCreateAnewEvent() throws Exception {
        TestUtils.sendKeys(Event_Title,prop.getProperty("EventTitle"));
        Thread.sleep(5000);
        TestUtils.clickElement(Event_StartDate);
        TestUtils.selectDate(Event_StartDate,1);
        TestUtils.clickElement(Event_StartTime);
        Thread.sleep(5000);
        /*for(WebElement element:Event_StartTime_All_TimeOptions)
        {
            if (element.getText().equalsIgnoreCase("06:00 pm"))
            {
                TestUtils.clickElement(element);
            }
        }*/
        TestUtils.clickElement(Event_StartTime_All_TimeOptions.get(0));
        TestUtils.clickElement(Event_EndDate);
        TestUtils.selectDate(Event_EndDate,1);
        TestUtils.clickElement(Event_endTime);
        Thread.sleep(5000);
        /*for(WebElement element:Event_StartTime_All_TimeOptions)
        {
            if (element.getText().equalsIgnoreCase("07:00 pm"))
            {
                TestUtils.clickElement(element);
            }
        }*/
        TestUtils.clickElement(Event_StartTime_All_TimeOptions.get(1));

        TestUtils.sendKeys(Invite_mail, prop.getProperty("mailId"));
        Invite_mail.sendKeys(Keys.ENTER);
        //TestUtils.clickElement(allowForward_Checkbox);
        TestUtils.sendKeys(Enter_Location, prop.getProperty("Location"));
        TestUtils.clickElement(add_Conference);
        TestUtils.waitForSpecificTime();
        Actions a=new Actions(driver);
        a.moveToElement(zohoMeeting_add_Conference).click().build().perform();
        //TestUtils.clickElement(zohoMeeting_add_Conference);
        TestUtils.waitForSpecificTime();
       // TestUtils.clickElement(selectAudio_add_Conference);
        Thread.sleep(5000);
        TestUtils.sendKeys(enter_Url, prop.getProperty("MeetingUrl"));
        TestUtils.scrollToBottom();
        TestUtils.explicitWait_waitTillElementIsClickable(enter_description);
        TestUtils.sendKeys(enter_description, prop.getProperty("description"));
        Thread.sleep(5000);
        TestUtils.clickElement(save_button);
        Thread.sleep(5000);
        if(attendeeOption_Radi0Button.isSelected())
        {
            TestUtils.clickElement(ConfirmButton_Submit);
        }
        else
        {
            TestUtils.clickElement(ConfirmButton_Submit);
        }

    }

    public void isEventDisplayed(String createdEvent) throws InterruptedException {
        Thread.sleep(5000);
        for (WebElement event : ListOf_AllEvents) {
            if (event.getText().equalsIgnoreCase(createdEvent)) {
                System.out.println("It is displayed" + event.isDisplayed());
                Assert.assertTrue(event.isDisplayed());
                break;
            }

        }
    }

    public void isEventDisplayed_afterSearch(String createdEvent) throws InterruptedException {
        Thread.sleep(5000);
       Assert.assertTrue(TestUtils.isElementClickable_NoException(driver.findElement(By.xpath("(//span[contains(text(),'" + createdEvent + "')])[1]"))));
    }

    public void click_On_SpecificEvent(String createdEvent) throws InterruptedException {
        for(WebElement event:ListOf_AllEvents)
        {
            if(event.getText().equalsIgnoreCase(createdEvent))
            {
                System.out.println("check the text" +event.getText() );
                TestUtils.clickElement(event);
                break;
            }
        }
        Thread.sleep(10000);
    }
    public void update_ExistingEvent(String newEvent) throws InterruptedException {
        TestUtils.clickElement(Event_Title);
        Event_Title.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        Event_Title.sendKeys(Keys.BACK_SPACE);
        TestUtils.sendKeys(Event_Title,newEvent);
        TestUtils.clickElement(Event_StartDate);
        TestUtils.selectDate(Event_StartDate,2);
        TestUtils.clickElement(Event_StartTime);
        /*for(WebElement element:Event_StartTime_All_TimeOptions)
        {
            if (element.getText().equalsIgnoreCase("06:00 pm"))
            {
                TestUtils.clickElement(element);
            }
        }*/
        TestUtils.clickElement(Event_StartTime_All_TimeOptions.get(0));
        TestUtils.clickElement(Event_EndDate);
        TestUtils.selectDate(Event_EndDate,2);
        TestUtils.clickElement(Event_endTime);
       /* for(WebElement element:Event_StartTime_All_TimeOptions)
        {
            if (element.getText().equalsIgnoreCase("07:00 pm"))
            {
                TestUtils.clickElement(element);
                break;
            }
        }*/
        TestUtils.clickElement(Event_StartTime_All_TimeOptions.get(1));
        TestUtils.clickElement(save_button);
        Thread.sleep(5000);

    }
    public void delete_Event() throws InterruptedException {
        TestUtils.clickElement(delete_Event_Button);
        TestUtils.clickElement(delete_confirm_Button);
        Thread.sleep(5000);
        TestUtils.clickElement(ConfirmButton_Submit);
        Thread.sleep(5000);
    }
    public void NoEventMessage_afterDeletion() throws InterruptedException {
        Assert.assertTrue(NoEvent_Message_AfterEventDelete.isDisplayed());
    }

}
