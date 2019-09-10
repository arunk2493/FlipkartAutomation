package com.tests;

import com.project.pages.SearchResultsPage;
import org.testng.annotations.Test;

public class PreRunner extends ProductSearch {

    @Test(priority = 9)
    public void removeProduct(){
        try {
            removeFromCart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
