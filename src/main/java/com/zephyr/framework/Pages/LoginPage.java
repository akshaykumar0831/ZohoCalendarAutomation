package com.zephyr.framework.Pages;

import com.zephyr.framework.Utils.TestUtils;
import com.zephyr.framework.base.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {

    @FindBy(xpath="//*[contains(text(),'SIGN IN')]")
    WebElement SignIn_Button;

    @FindBy(id="login_id")
    WebElement EmailId_TextBox;

    @FindBy(id="password")
    WebElement Password_TextBox;

    @FindBy(id="nextbtn")
    WebElement SignIn_Button_LoginPage;


    @FindBy(id="nextbtn")
    WebElement Next_Button;

    public static Logger logger = Logger.getLogger(LoginPage.class);


    public LoginPage()
    {
        PageFactory.initElements(driver,this);
    }

    public void clickOn_SignIn_HomePage()
    {
        TestUtils.clickElement(SignIn_Button);
    }

    public void Enter_UserName_Password()
    {
        TestUtils.sendKeys(EmailId_TextBox, prop.getProperty("username"));
        TestUtils.clickElement(Next_Button);
        TestUtils.sendKeys(Password_TextBox, prop.getProperty("password"));
        TestUtils.clickElement(SignIn_Button_LoginPage);
    }


}
