package com.tests;

import com.project.pages.SearchResultsPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class ProductSearch extends LoginRunner {
    @Test(priority = 4)
    public void searchProduct(){
        try {
            searchProduct("productName");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test(priority = 5)
    @Parameters("productNumber")
    public void selectProductPage(int productNumber){
        try {
            selectProduct(productNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test(priority = 6)
    public void verifyProduct(){
        try {
            verifyProductPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test(priority = 7)
    public void verifyProductCart(){
        try {
            verifyCartPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test(priority = 8)
    public void cartItemCount(){
        try {
            verifyCartItemCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
