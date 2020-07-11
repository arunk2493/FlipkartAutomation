package com.project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AutomationPractise extends SearchResultsPage {
    @FindBy(xpath = "//a[@title='Log in to your customer account']")
    public WebElement txtSearch1;

    protected void signAutomation() throws InterruptedException {
        txtSearch1.click();
        Thread.sleep(10000);
        String s1 = driver.getTitle();
        System.out.println(s1);
    }
}

