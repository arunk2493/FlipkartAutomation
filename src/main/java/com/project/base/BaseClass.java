package com.project.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestMethodFinder;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.internal.Utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class BaseClass extends ReusableMethods {

    private Utils FileUtils;
    public ITestContext suites;

    @BeforeSuite
    public WebDriver initiateTest(){
        String browserValue = getValue("Browser");
        switch (browserValue){
            case "Chrome":
                System.setProperty("webdriver.chrome.driver",path+"/drivers/chromedriver");
                driver = new ChromeDriver();
                driver.get(getValue("URL"));
                break;
            case "FireFox":
                System.setProperty("webdriver.gecko.driver",path+"/drivers/geckodriver");
                driver = new FirefoxDriver();
                driver.get(getValue("URL"));
                break;
        }
        return driver;
    }
    @BeforeTest
    public void testConfigs(ITestContext suite){

        String suitename = suite.getSuite().getName().toString();
        String timeValue = getTimeStamp();
        String testName = suite.getCurrentXmlTest().getName().toString();
        System.out.println("The current Execution is "+suitename+" and the test is "+testName+ " at "+timeValue);
    }
    @BeforeMethod
    public WebDriver initiateDrivers(ITestResult result)
    {
        String methodname = result.getMethod().getMethodName().toString();
        System.out.println("The current method is: "+methodname);
        PageFactory.initElements(driver,this);
        return driver;
    }
    @AfterTest
    public void closeBrowser(){
        driver.close();
    }
    @AfterSuite
    public void tearDown(){
        driver.quit();
    }
}
