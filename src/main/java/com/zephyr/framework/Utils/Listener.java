package com.zephyr.framework.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.ITestNGListener;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.Set;


public class Listener  implements ITestNGListener,WebDriverListener   {

    @Override
    public void beforeAnyWebDriverCall(WebDriver driver, Method method, Object[] args) {
        System.out.println("beforeAnyWebDriverCall");
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        System.out.println("after get");
    }

    @Override
    public void afterAnyWebDriverCall(WebDriver driver, Method method, Object[] args, Object result) {
    }

    @Override
    public void beforeGet(WebDriver driver, String url) {
    }


    @Override
    public void beforeGetCurrentUrl(WebDriver driver) {
        System.out.println("Before getting current URL");
    }


    @Override
    public void afterGetCurrentUrl(WebDriver driver, String result) {
        System.out.println("After getting current URL: " + result);
    }


    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        System.out.println("Before finding element with locator: " + locator);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        System.out.println("After finding element with locator: " + locator + ". Found: " + result);
    }

    @Override
    public void beforeFindElements(WebDriver driver, By locator) {
        System.out.println("Before finding elements with locator: " + locator);
    }

    @Override
    public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
        System.out.println("After finding elements with locator: " + locator + ". Found " + result.size() + " elements");
    }


    @Override
    public void beforeClose(WebDriver driver) {
        System.out.println("Before closing the WebDriver instance");
    }

    @Override
    public void afterClose(WebDriver driver) {
        System.out.println("After closing the WebDriver instance");
    }

    @Override
    public void beforeQuit(WebDriver driver) {
        System.out.println("Before quitting the WebDriver instance");
    }

    @Override
    public void afterQuit(WebDriver driver) {
        System.out.println("After quitting the WebDriver instance");
    }

    @Override
    public void beforeGetWindowHandles(WebDriver driver) {
        System.out.println("Before getting window handles");
    }

    @Override
    public void afterGetWindowHandles(WebDriver driver, Set<String> result) {
        System.out.println("After getting window handles. Number of handles: " + result.size());
    }

    @Override
    public void beforeGetWindowHandle(WebDriver driver) {
        System.out.println("Before getting window handles");
    }

    @Override
    public void afterGetWindowHandle(WebDriver driver, String result) {
        System.out.println("Before getting window handles");
    }

    @Override
    public void beforeExecuteScript(WebDriver driver, String script, Object[] args) {
        System.out.println("Before executing synchronous script: " + script);
    }

    @Override
    public void afterExecuteScript(WebDriver driver, String script, Object[] args, Object result) {
        System.out.println("After executing synchronous script: " + script);
    }

    @Override
    public void beforeExecuteAsyncScript(WebDriver driver, String script, Object[] args) {
        System.out.println("Before executing asynchronous script: " + script);
    }

    @Override
    public void afterExecuteAsyncScript(WebDriver driver, String script, Object[] args, Object result) {
        System.out.println("After executing asynchronous script: " + script);
    }

    @Override
    public void beforeAnyWebElementCall(WebElement element, Method method, Object[] args) {
        System.out.println("Before calling method " + method.getName() + " on WebElement: " + element);
    }

    @Override
    public void afterAnyWebElementCall(WebElement element, Method method, Object[] args, Object result) {
        System.out.println("After calling method " + method.getName() + " on WebElement: " + element);
    }



    @Override
    public void beforeSubmit(WebElement element) {
        System.out.println("Before submitting form element: " + element);
    }

    @Override
    public void afterSubmit(WebElement element) {
        System.out.println("After submitting form element: " + element);
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        System.out.println("Before sending keys to element: " + element);
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        System.out.println("After sending keys to element: " + element);
    }

    @Override
    public void beforeClear(WebElement element) {
        System.out.println("Before clearing text from element: " + element);
    }

    @Override
    public void afterClear(WebElement element) {
        System.out.println("After clearing text from element: " + element);
    }

    @Override
    public void beforeGetAttribute(WebElement element, String name) {
        System.out.println("Before getting attribute '" + name + "' from WebElement: " + element);
    }

    @Override
    public void afterGetAttribute(WebElement element, String name, String result) {
        System.out.println("After getting attribute '" + name + "' from WebElement: " + element + ". Result: " + result);
    }

    @Override
    public void beforeIsSelected(WebElement element) {
        System.out.println("Before checking if WebElement is selected: " + element);
    }

    @Override
    public void afterIsSelected(WebElement element, boolean result) {
        System.out.println("After checking if WebElement is selected: " + element + ". Result: " + result);
    }

    @Override
    public void beforeIsEnabled(WebElement element) {
        System.out.println("Before checking if WebElement is enabled: " + element);
    }

    @Override
    public void afterIsEnabled(WebElement element, boolean result) {
        System.out.println("After checking if WebElement is enabled: " + element + ". Result: " + result);
    }

    @Override
    public void beforeGetText(WebElement element) {
        System.out.println("Before getting text from WebElement: " + element);
    }

    @Override
    public void afterGetText(WebElement element, String result) {
        System.out.println("After getting text from WebElement: " + element + ". Result: " + result);
    }

    @Override
    public void beforeAnyNavigationCall(WebDriver.Navigation navigation, Method method, Object[] args) {
        System.out.println("Before any navigation call: " + method.getName());
    }

    @Override
    public void afterAnyNavigationCall(WebDriver.Navigation navigation, Method method, Object[] args, Object result) {
        System.out.println("After any navigation call: " + method.getName());
    }

    @Override
    public void beforeClick(WebElement element) {
        System.out.println("Before clicking on element: " + element);
    }

    @Override
    public void afterClick(WebElement element) {
        System.out.println("After clicking on element: " + element);
    }


    @Override
    public void beforeTo(WebDriver.Navigation navigation, String url) {
        System.out.println("Before navigating to URL: " + url);
    }

    @Override
    public void afterTo(WebDriver.Navigation navigation, String url) {
        System.out.println("After navigating to URL: " + url);
    }

    @Override
    public void beforeTo(WebDriver.Navigation navigation, URL url) {
        System.out.println("Before navigating to URL: " + url);
    }

    @Override
    public void afterTo(WebDriver.Navigation navigation, URL url) {
        System.out.println("After navigating to URL: " + url);
    }





}
