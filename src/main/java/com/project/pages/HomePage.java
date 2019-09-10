package com.project.pages;

import com.aventstack.extentreports.Status;
import com.project.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class HomePage extends BaseClass {
    @FindBy(linkText = "Login & Signup")
    public WebElement loginLink;
    @FindBy(xpath = "//span[text()='Enter Email/Mobile number']/parent::label/parent::div/input[1]")
    public WebElement txtUserName;
    @FindBy(xpath = "//span[text()='Enter Password']/parent::label/parent::div/input[1]")
    public WebElement txtPassWord;
    @FindBy(xpath = "//span[text()='Login']/parent::button[1]")
    public WebElement btnLogin;

    public void verifyTitle() throws IOException {
        String title = driver.getTitle();
        Assert.assertEquals(title,getValue("Title"));
        if(title.equals(getValue("Title"))){
            tests.log(Status.PASS,"Title Verification");

        }else{
            tests.log(Status.FAIL,"Title Verification");
            capture(driver);
        }
    }
    public void enterCredentials() throws IOException {
        try{
            boolean loginWindow = txtUserName.isDisplayed();
            if(loginWindow != true){
                loginLink.click();
                Thread.sleep(2000);
            }
            txtUserName.sendKeys(getValue("Username"));
            txtPassWord.sendKeys(getValue("Password"));
            btnLogin.click();
            Thread.sleep(3000);
            tests.log(Status.PASS,"Login");
        }catch (Exception e){
            tests.log(Status.FAIL,"Login");
            capture(driver);
            e.printStackTrace();
        }
    }
    public void verifyLogin() throws IOException {
        String userName = getValue("Name");
        boolean verifyLoginStatus = driver.findElement(By.xpath("//div[text()='"+userName+"']")).isDisplayed();
        if(verifyLoginStatus==true){
            Assert.assertEquals(verifyLoginStatus,true);
            tests.log(Status.PASS,"Verify User");
        }
        else{
            Assert.assertFalse(verifyLoginStatus);
            tests.log(Status.FAIL,"Verify User");
            capture(driver);
        }
    }
    public void logoutUser(){
        String userName = getValue("Name");
        boolean verifyLoginStatus = driver.findElement(By.xpath("//div[text()='"+userName+"']")).isDisplayed();
        if(verifyLoginStatus==true){

        }

    }
}
