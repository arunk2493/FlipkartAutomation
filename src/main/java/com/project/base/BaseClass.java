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
        suiteFolder = suitename;
        createDirectory(suiteFolder);
        testName = suite.getCurrentXmlTest().getName().toString();
        String htmlName = testName+"_"+timeValue+".html";
       reporter = new ExtentHtmlReporter(suitePath+"/"+suiteFolder+"/"+htmlName);
       reports = new ExtentReports();
       reports.attachReporter(reporter);
       tests = reports.createTest(testName);
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
    public void closeReports(){

        reports.flush();
    }
    @AfterSuite
    public void tearDown(){
        driver.close();
        driver.quit();

    }
    public String capture(WebDriver driver) throws IOException
    {
        String timeValue = getTimeStamp();
        //String testName = suites.getCurrentXmlTest().getName().toString();
        String screenShotNames = testName+"_"+timeValue;
        createDirectory(suiteFolder);
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = path+"/Screenshots/"+suiteFolder+"/"+screenShotNames+".png";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);
        return dest;
    }
}
