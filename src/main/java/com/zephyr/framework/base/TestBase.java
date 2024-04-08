package com.zephyr.framework.base;

import com.zephyr.framework.Utils.Listener;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.events.EventFiringDecorator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

public class TestBase {

    public static Properties prop;
    public static WebDriver driver;
    public static Listener eventListener;


    public static Logger logger = Logger.getLogger(TestBase.class);
    private static String url;// declared url variable to use in the method to handle multiple way of url input

    public TestBase() {
        try {
            prop = new Properties();

            FileInputStream fs = new FileInputStream("src/main/resources/config/config.properties");
            prop.load(fs);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getUrl() {
        String url = null;
        FileInputStream input = null;
        // check if URL is passed as a command line argument
        if (System.getProperty("url") != null) {
            url = System.getProperty("url");
        }
        // if URL is not passed as a command line argument, try to read it from the config file
        if (url == null) {
            try {
                url = prop.getProperty("URL_fromConfig");
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException ex) {
                        System.out.println("Error closing input stream: " + ex.getMessage());
                    }
                }
            }
        }
        return url;
    }

    public static void initialization ()  {
        String browserName = prop.getProperty("browser");
        if (browserName.equalsIgnoreCase("chrome")) {

            PropertyConfigurator.configure("src/main/resources/log4j/log4j.properties");
            ChromeOptions options = new ChromeOptions();
            //options.setExperimentalOption("prefs", chromePrefs);
            LoggingPreferences logPrefs = new LoggingPreferences();//added to print the logs in console
            logPrefs.enable(LogType.BROWSER, Level.ALL);
            options.setCapability("goog:loggingPrefs", logPrefs);
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        } else {
            //may need to add chromePrefs if required

            PropertyConfigurator.configure("src/main/resources/log4j/log4j.properties");
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
            options.addArguments("--headless");
            options.addArguments("start-maximized"); // open Browser in maximized mode
            options.addArguments("--window-size=1400,2100");
            options.addArguments("--disable-gpu"); // applicable to Windows os only
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--crash-dumps-dir=/tmp");
            options.addArguments("--remote-allow-origins=*");
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.BROWSER, Level.ALL);
            //options.setExperimentalOption("prefs", chromePrefs);
                    /*options.addArguments("webdriver.chrome.driver", "/snap/bin/chromium.chromedriver");
                    LoggingPreferences logPrefs = new LoggingPreferences();
                    logPrefs.enable(LogType.BROWSER, Level.ALL);
                    options.addArguments("--no-sandbox");
                    options.addArguments("--headless");
                    options.addArguments("--crash-dumps-dir=/tmp");
                    options.setExperimentalOption("useAutomationExtension", false);//added
                    options.addArguments("--disable-extensions");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--profile-directory=Default");//added
                    options.addArguments("--user-data-dir=~/.config/google-chrome");//added
                    //options.addArguments("--remote-allow-origins=*");
                    options.addArguments("--remote-debugging-port=9222");
                    options.addArguments("--ignore-ssl-errors=yes");//added
                    options.addArguments("--ignore-certificate-errors");//added
                    options.setCapability("goog:loggingPrefs", logPrefs);
                    options.addArguments("--window-size=1400,2100");*/ //!!!should be enabled for Jenkins
            driver = new ChromeDriver(options);



        }


        eventListener = new Listener();
        EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator<>(eventListener);
        driver = decorator.decorate(driver);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        url = getUrl();
        driver.get(url);
        logger.info("URL launched");

    }


}
