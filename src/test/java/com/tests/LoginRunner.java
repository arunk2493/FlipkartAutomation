package com.tests;

import com.project.pages.HomePage;
import com.project.pages.SearchResultsPage;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginRunner extends SearchResultsPage {
    @Test(priority = 1)
    public void verifyPage(){
        try{
            verifyTitle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test(priority = 2)
    public void login(){
        try {
            enterCredentials();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test(priority = 3)
    public void verifyUser() throws InterruptedException {
        try{
            verifyLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
