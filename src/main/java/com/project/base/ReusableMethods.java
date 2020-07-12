package com.project.base;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.Utils;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class ReusableMethods {

    public WebDriver driver;
    public String propvalue = null;
    public String path = System.getProperty("user.dir");
    public String firstName=null;
    public String lastName = null;
    public String userName = null;
    public String windowName = null;
    public String newURL = null;
    Properties prop = new Properties();
    public ExtentReports reports = null;
    public ExtentTest tests = null;
    public ExtentHtmlReporter reporter = null;
    public String suitePath = path+"/Reports";
    private Utils FileUtils;
    public String searchItems = null;
    public int itemCount;
    public Map<String, List<String>> productDescription = new HashMap<String, List<String>>();
    public Map<String,List<String>> itemDetails = new HashMap<String, List<String>>();
    public List<String> itemName = new ArrayList<String>();
    public List<String> itemPrice = new ArrayList<String>();
    public List<String> itemDescription = new ArrayList<String>();
    public String item =null;
    public String key = null;
    public List<String> values = null;
    public String searchPageProductName = null;
    public String productNameValue = null;
    public String productPriceValue = null;
    public int cartSize;
    public int searchRecords;
    public String b=null;
    public String cartCount=null;

    public String getValue(String keyValue)  {
        String propFile =path+"//src//main//resources//data.properties";
        try{
            FileInputStream fis =  new FileInputStream(propFile);
            prop.load(fis);
            propvalue = prop.getProperty(keyValue);
        }catch (Exception e){
            e.printStackTrace();
        }
        return propvalue;
    }
    public String getTimeStamp(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        String timeDate = dtf.format(now).toString();
        return timeDate;
    }
    public void scrollToElement(WebElement we){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",we);
    }
    public void waitForPageLoad(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void waitForWebElement(WebElement e){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOf(e));
    }
    public void hoverElement(WebElement we,WebDriver d){
        Actions a = new Actions(d);
        a.moveToElement(we).perform();
    }
    public void switchWindows(WebDriver d){
        windowName = d.getWindowHandle();
        d.switchTo().window(windowName);
        newURL = d.getTitle();
    }
    public void closeWindows(WebDriver d){
        d.close();
        windowName = d.getWindowHandle();
        d.switchTo().window(windowName);
    }

}
