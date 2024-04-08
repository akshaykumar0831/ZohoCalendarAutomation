package com.zephyr.framework.Utils;

import com.zephyr.framework.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestUtils extends TestBase {
    public static DataFormatter formatter= new DataFormatter();

    public static String takeScreenshotAtEndOfTest() throws IOException {
        // Generate file name with timestamp
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = "Screenshot_" + timeStamp + ".png";

        // Define the target directory
        String targetDirectory = "src/main/resources/Screenshots";

        // Ensure that the target directory exists; create it if it doesn't
        File directory = new File(targetDirectory);
        if (!directory.exists()) {
            directory.mkdirs(); // Create directory and any necessary parent directories
        }

        // Define the full path of the screenshot
        String screenshotPath = targetDirectory + "/" + screenshotName;

        // Take screenshot and save to file
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(screenshotPath));

        return screenshotPath; // Return path of the saved screenshot
    }

    public static void selectTextFromDropDown(WebElement element, String text)
    {
        Select s =new Select(element);
        s.selectByVisibleText(text);
    }

    public static void acceptAlert()
    {
        driver.switchTo().alert().accept();
    }

    public static void clickElement(WebElement webElement) {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
            //webElement.click();
            logger.debug("Clicking on " + webElement.getText());
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", webElement);
        } catch (Exception e) {
            logger.error(e.toString());
            Assert.fail("Element is not present");
        }
    }

    public static double getDoubleValue(String s) {
        double value = 0;
        try {
            Pattern p2 = Pattern.compile("[^0-9]*([0-9]*,?([0-9]+(\\.[0-9]*))?)");
            Matcher m2 = p2.matcher(s);
            m2.matches();
            String s_num2 = m2.group(1).replaceAll("[^\\d.]", "");
            value=Double.valueOf(s_num2);

        } catch (Exception e) {
            logger.error(e.toString());
            Assert.fail("Element is not present");
        }
        return value;

    }
    public static double getRoundedValue(double d) {
        double v = 0;
        try {
            v = Math.round(d * 100) / (double) 100;

        } catch (Exception e) {
            logger.error(e.toString());
            Assert.fail("Element is not present");
        }
        return v;

    }

    public static void scrollDown(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixels + ")");
    }

    public static void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void sendKeys(WebElement webElement, String keys) {
        try {
            //    Thread.sleep(5000);
            WebDriverWait webDriverWait = new WebDriverWait(driver,Duration.ofSeconds(30));
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
            //webElement.click();
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", webElement);
            webElement.clear();
            webElement.sendKeys(keys);
        } catch (Exception e) {
            logger.error(e.toString());
            Assert.fail("Not able to enter text " + keys);
        }
    }


    public static void waitForFullPageLoad() {
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("return document.readyState").toString().equals("complete");
        logger.info("Page loaded succesfully");
    }

    public static void waitForSpecificTime() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static boolean waitFluently_WithException(WebElement element) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.withTimeout(Duration.ofSeconds(30));
        wait.pollingEvery(Duration.ofSeconds(5));
        wait.ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
        return false;
    }

    public static boolean waitFluently_NoException(WebElement element) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
            wait.withTimeout(Duration.ofSeconds(30));
            wait.pollingEvery(Duration.ofSeconds(5));
            wait.ignoring(NoSuchElementException.class);
            wait.ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isElementClickable_WithException(WebElement element) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.withTimeout(Duration.ofSeconds(30));
        wait.pollingEvery(Duration.ofSeconds(5));
        wait.ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return false;
    }
    public static boolean isElementClickable_NoException(WebElement element) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
            wait.withTimeout(Duration.ofSeconds(30));
            wait.pollingEvery(Duration.ofSeconds(5));
            wait.ignoring(NoSuchElementException.class);
            wait.ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isElementPresent_NoException(WebElement element) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
            wait.withTimeout(Duration.ofSeconds(30));
            wait.pollingEvery(Duration.ofSeconds(5));
            wait.ignoring(NoSuchElementException.class);
            wait.ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static void explicitWait_VisibilityOfElement(WebElement element) throws Exception
    {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver,Duration.ofSeconds(30));
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void explicitWait_waitTillElementIsClickable(WebElement element) throws Exception
    {
        try {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static boolean areElementsVisible(List<WebElement> elements)
    {
        for(WebElement element:elements)
        {
            if(!element.isDisplayed())
            {
                return false;
            }
        }
        return true;
    }

    public static boolean areElementsVisibleWithCount(WebElement[] elements) {
        int i = 0;
        for (WebElement element : elements) {
            if (!element.isDisplayed()) {
                System.out.println("Element" + i + "is not displayed");
                return false;
            }
            i++;
        }
        return true;
    }

    public static void clear_the_TextBox(WebElement element) {
        clickElement(element);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.BACK_SPACE);
    }

    public static void selectDate(WebElement element,int days)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d"); // Example: 29March
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, days); // Add 7 days for a week ahead
        String futureDateText = dateFormat.format(calendar.getTime());

        // Find and select the future date in the calendar
        WebElement futureDateElement = null;
        while (futureDateElement == null) {
            try {
                // Construct XPath dynamically based on future date
                String xpath = "//button[@data-date='" + futureDateText + "']";
                futureDateElement = driver.findElement(By.xpath(xpath));
                futureDateElement.click();
            } catch (StaleElementReferenceException e) {
                // Re-find the "Validate Up To" button
                TestUtils.clickElement(element);
            }
        }
    }

}
