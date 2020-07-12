package com.tests;

import com.project.pages.ProductDetails;
import com.project.pages.UserDetails;
import org.testng.annotations.Test;

public class AutomationRunner extends ProductDetails {
    @Test
    public void signInUser() throws InterruptedException {
        createUser();
    }
    @Test
    public void enterBillingInfo() throws InterruptedException {
        createUserInformation();
    }
    @Test
    public void zipCodeValidationInvalid() throws InterruptedException {
        zipCodeValidation();
        invalidCountrySelection();
    }
    @Test
    public void validZipCodeValidation() throws InterruptedException {
        zipCode();
    }
    @Test
    public void registerUserAccount() throws InterruptedException {
        registerAccount();
    }
    @Test
    public void signOff() throws InterruptedException {
        signOut();
    }
    @Test
    public void signInInvalid() throws InterruptedException {
        signInInvalidUser();
    }
    @Test
    public void signInValid() throws InterruptedException {
        signInUserValid();
    }
    @Test
    public void searchProducts() throws InterruptedException {
        searchProduct();
        verifySearchCount();
        verifySearchedProduct();
    }
    @Test
    public void selectManufacturers1AndProceedToCheckout() throws InterruptedException {
        selectManufacturer();
        signOff();

    }
    @Test
    public void selectManufacturers2AndProceedToCheckout() throws InterruptedException {
        signInValid();
        searchProduct();
        selectManufacturer2();
        signOff();
    }
    @Test
    public void selectManufacturers3AndProceedToCheckout() throws InterruptedException {
        signInValid();
        searchProduct();
        selectManufacturer3();
        proceedToCheckOut();
        verifyOrderDetails();
    }
    @Test
    public void reviewProduct() throws InterruptedException {
        writeReview();
    }
    @Test
    public void shareToSocialMedia() throws InterruptedException {
        shareToFacebook();
        shareToGooglePlus();
        shareToPinterest();
    }
}
