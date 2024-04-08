package com.zephyr.framework.Pages;

import com.zephyr.framework.Utils.TestUtils;
import com.zephyr.framework.base.TestBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeedBackPage extends TestBase {

    File imageFile_TobeUploaded = new File("src/main/java/com/zephyr/framework/testData/Upwork 2.jpeg");


    @FindBy(xpath="(//*[contains(text(),'Subject')]/following::div/input)[1]")
    WebElement subject_feedback;

    @FindBy(xpath="//*[contains(text(),'Details')]/following::div/textarea")
    WebElement description_feedback;

    @FindBy(xpath="//*[@type='file']")
    WebElement chooseButton_feedback;

    @FindBy(xpath="//button[@class='zmbtn__xwshh3 zmbtn--btn__1fur6hf zmbtn--filled__1xp6yha zmbtn--primary__tokph1 zmbtn--md__gjt7']")
    WebElement sendButton_feedback;

    @FindBy(xpath="//*[contains(text(),'Feedback')]")
    WebElement Feedback_Header;

    @FindBy(xpath="//*[contains(text(),'Thank you for sending us your feedback')]")
    WebElement Feedback_submit_successfulMessage;


    public FeedBackPage()
    {

        PageFactory.initElements(driver,this);
    }


    public void send_feedback() throws Exception {
        TestUtils.explicitWait_VisibilityOfElement(Feedback_Header);
        TestUtils.sendKeys(subject_feedback, prop.getProperty("subject_feedback"));
        TestUtils.sendKeys(description_feedback, prop.getProperty("description_feedback"));
        TestUtils.waitForSpecificTime();
        chooseButton_feedback.sendKeys(imageFile_TobeUploaded.getAbsolutePath());
        Thread.sleep(5000);
        TestUtils.clickElement(sendButton_feedback);
        TestUtils.explicitWait_VisibilityOfElement(Feedback_submit_successfulMessage);
        Assert.assertEquals(Feedback_submit_successfulMessage.getText(),"Thank you for sending us your feedback");


    }


}
