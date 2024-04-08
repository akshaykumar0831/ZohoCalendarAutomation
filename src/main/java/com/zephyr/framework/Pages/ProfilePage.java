package com.zephyr.framework.Pages;

import com.zephyr.framework.Utils.TestUtils;
import com.zephyr.framework.base.TestBase;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProfilePage extends TestBase {

    @FindBy(xpath="//*[@id='editprofile']")
    WebElement Editprofile_button_calender;

    @FindBy(xpath="//*[@id='profile_Fname_edit']")
    WebElement FirstName_Editprofile;

    @FindBy(xpath="//*[@id='profile_Lname_edit']")
    WebElement LastName_Editprofile;

    @FindBy(xpath="//*[@id='profile_nickname']")
    WebElement displayName_Editprofile;

    @FindBy(xpath="//div[@jsid='gender_select'][@class='selectbox basic_selectbox']")
    WebElement Gender_Editprofile;

    @FindAll({@FindBy(xpath="(//*[@jsid='gender_select'])/child::*[@class='option']")})
    List<WebElement> DropDown_Options_GenderItem;

    @FindBy(xpath="//div[@jsid='localeCn'][@class='selectbox basic_selectbox']")
    WebElement country_Editprofile;

    @FindBy(xpath="//input[@jsid='localeCn'][@class='select_search_input']")
    WebElement country_valueEntry_Editprofile;

    @FindBy(xpath="//div[@jsid='localeLn'][@class='selectbox basic_selectbox']")
    WebElement language_Editprofile;

    @FindBy(xpath="//input[@jsid='localeLn'][@class='select_search_input']")
    WebElement language_valueEntry_Editprofile;

    @FindBy(xpath="//*[@id='saveprofile']")
    WebElement save_button_Editprofile;

    @FindBy(xpath="//div[@id='profile_name']")
    WebElement updatedProfile_Editprofile;

    @FindAll({@FindBy(xpath="//a[@class='zmbtn__xwshh3 zmbtn--mbtn__h38rht zmbtn--flat__1brk1qf zmbtn--default__yc2jlc zmbtn--md__gjt7' and not(contains(@href, 'accounts.zoho'))]")})
    List<WebElement> webpages_Profile;







    public ProfilePage()
    {

        PageFactory.initElements(driver,this);
    }

    public void update_Profile()
    {
        Set<String> windowHandles = driver.getWindowHandles();
        // Switch to the new tab (assuming it's the only new window opened)
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
        }

        TestUtils.clickElement(Editprofile_button_calender);
        TestUtils.clear_the_TextBox(FirstName_Editprofile);
        TestUtils.sendKeys(FirstName_Editprofile,prop.getProperty("firstName"));
        TestUtils.clear_the_TextBox(LastName_Editprofile);
        TestUtils.sendKeys(LastName_Editprofile,prop.getProperty("lastname"));
        TestUtils.clear_the_TextBox(displayName_Editprofile);
        TestUtils.sendKeys(displayName_Editprofile,prop.getProperty("displayname"));
        TestUtils.clickElement(Gender_Editprofile);
        TestUtils.clickElement(DropDown_Options_GenderItem.get(0));
        TestUtils.clickElement(country_Editprofile);
        TestUtils.sendKeys(country_valueEntry_Editprofile,prop.getProperty("country"));
        TestUtils.clickElement(language_Editprofile);
        TestUtils.sendKeys(language_valueEntry_Editprofile,prop.getProperty("Language"));
        TestUtils.clickElement(save_button_Editprofile);

    }
    public void checkIf_ProfileUpdated() throws InterruptedException {
        TestUtils.waitForSpecificTime();
        Thread.sleep(5000);
        String updatedFullName = updatedProfile_Editprofile.getText().trim();
        String expectedFullName = prop.getProperty("firstName") +" "+ prop.getProperty("lastname");
        System.out.println("updated name" +updatedFullName );
        System.out.println("expected name" +expectedFullName );

        // Validate whether the updated full name matches the expected full name
        if (updatedFullName.equals(expectedFullName)) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }

    }

    public void check_profile_WebPages() throws InterruptedException {
        TestUtils.waitForSpecificTime();
        String parentTab = driver.getWindowHandle();
        // Open each link in a new tab and validate the URL
        for (int i = 0; i < webpages_Profile.size(); i++) {

            TestUtils.clickElement(webpages_Profile.get(i));
            Thread.sleep(5000);
            // Switch to the new tab
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            for (String tab : tabs) {
                if (!tab.equals(parentTab)) {
                    driver.switchTo().window(tab);
                    break;
                }
            }

            // Validate URL based on index
            switch (i) {
                case 0:
                    String expectedUrl_howto = prop.getProperty("HowToLink"); // Change this to the expected URL
                    String currentUrl_howto = driver.getCurrentUrl();

                    System.out.println("expectedUrl : " +expectedUrl_howto );
                    System.out.println("currentUrl : " +currentUrl_howto );

                    if (currentUrl_howto.equals(expectedUrl_howto)) {
                        Assert.assertTrue(true);
                    } else {
                        Assert.assertTrue(false);
                    }
                    break;
                case 1:
                    String expectedUrl_help = prop.getProperty("HelpLink"); // Change this to the expected URL
                    String currentUrl_help = driver.getCurrentUrl();

                    System.out.println("expectedUrl : " +expectedUrl_help );
                    System.out.println("currentUrl : " +currentUrl_help );

                    if (expectedUrl_help.equals(currentUrl_help)) {
                        Assert.assertTrue(true);
                    } else {
                        Assert.assertTrue(false);
                    }
                    break;
                case 2:
                    String expectedUrl_blog = prop.getProperty("blogLink"); // Change this to the expected URL
                    String currentUrl_help_blog = driver.getCurrentUrl();

                    System.out.println("expectedUrl : " +expectedUrl_blog );
                    System.out.println("currentUrl : " +currentUrl_help_blog );

                    if (currentUrl_help_blog.equals(expectedUrl_blog)) {
                        Assert.assertTrue(true);
                    } else {
                        Assert.assertTrue(false);
                    }
                    break;
                case 3:
                    String expectedUrl_community = prop.getProperty("CommunityLink"); // Change this to the expected URL
                    String currentUrl_community = driver.getCurrentUrl();

                    System.out.println("expectedUrl : " +expectedUrl_community );
                    System.out.println("currentUrl : " +currentUrl_community );

                    if (currentUrl_community.equals(expectedUrl_community)) {
                        Assert.assertTrue(true);
                    } else {
                        Assert.assertTrue(false);
                    }
                    break;
                default:
                    System.out.println("Invalid link index");
                    break;
            }

            // Close the new tab
            driver.close();

            // Switch back to the original tab
            driver.switchTo().window(parentTab);
        }
    }

}
