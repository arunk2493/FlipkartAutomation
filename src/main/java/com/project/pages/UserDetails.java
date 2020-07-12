package com.project.pages;

import com.project.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.Console;

public class UserDetails extends BaseClass {
    @FindBy(xpath = "//a[@title='Log in to your customer account']")
    public WebElement linkSignIn;
    @FindBy(xpath = "//a[@title='Log me out']")
    public WebElement linkSignout;
    @FindBy(id = "email_create")
    public WebElement inputEmailId;
    @FindBy(id="SubmitCreate")
    public WebElement btnCreateUser;
    @FindBy(xpath = "//h1[@class='page-heading']")
    public WebElement txtHeader;
    @FindBy(id="customer_firstname")
    public WebElement txtCustomerFirstName;
    @FindBy(id="customer_lastname")
    public WebElement txtCustomerLastName;
    @FindBy(id="email_create")
    public WebElement txtEmailId;
    @FindBy(id="passwd")
    public WebElement txtPassword;
    @FindBy(id="firstname")
    public WebElement txtBillFirstName;
    @FindBy(id="lastname")
    public WebElement txtBillLastName;
    @FindBy(id="company")
    public WebElement txtCompany;
    @FindBy(id="address1")
    public WebElement txtAddress;
    @FindBy(id="city")
    public WebElement txtCity;
    @FindBy(id="id_state")
    public WebElement dropDownState;
    @FindBy(id="postcode")
    public WebElement txtZipCode;
    @FindBy(id="id_country")
    public WebElement dropDownCountry;
    @FindBy(id="phone_mobile")
    public WebElement txtMobileNumber;
    @FindBy(id="alias")
    public WebElement txtAliasAddress;
    @FindBy(xpath = "//h1[@class='page-heading']/../div[1]/ol/li")
    public WebElement txtErrorMessage;
    @FindBy(id = "submitAccount")
    public WebElement btnRegister;
    @FindBy(xpath = "//label[@for='email']/../input[1]")
    public WebElement inputUserEmailId;
    @FindBy(id = "SubmitLogin")
    public WebElement btnSignIn;
    @FindBy(xpath = "//a[@title='View my customer account']/span")
    public WebElement txtCustomerName;
    @FindBy(id = "id_gender1")
    public WebElement radioMr;
    @FindBy(id = "id_gender2")
    public WebElement radioMrs;


    public void createUser() throws InterruptedException {
        try {
            linkSignIn.click();
            waitForPageLoad();
            inputEmailId.sendKeys(getValue("EmailId"));
            btnCreateUser.click();
            waitForPageLoad();
            if(txtHeader.getText().equals("Create an account")){
                if(getValue("Gender").equals("Male")){
                    radioMr.click();
                }
                else
                {
                    radioMrs.click();
                }
                txtCustomerFirstName.sendKeys(getValue("FirstName"));
                txtCustomerLastName.sendKeys(getValue("LastName"));
                if(txtEmailId.getAttribute("value").equals(getValue("EmailId"))){
                    Assert.assertEquals(txtEmailId.getAttribute("value"),getValue("EmailId"));
                }
                else {
                    Assert.assertFalse(false);
                }
                txtPassword.sendKeys(getValue("Password"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void createUserInformation() throws InterruptedException {
        try {
            txtCompany.sendKeys(getValue("Company"));
            txtAddress.sendKeys(getValue("Address"));
            txtCity.sendKeys(getValue("City"));
            Select s1 = new Select(dropDownState);
            s1.selectByVisibleText(getValue("Alabama"));
            System.out.println("----Using Invalid Zip Code----");
            txtMobileNumber.sendKeys(getValue("MobileNumber"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void zipCodeValidation(){
        txtZipCode.sendKeys(getValue("InvalidZipCode"));
        btnRegister.click();
        waitForPageLoad();
        System.out.println("----Verify Invalid Zip Code Error----");
        if(txtErrorMessage.isDisplayed()){
            System.out.println("There is an error in the Zipcode Format: "+txtErrorMessage.getText());
            System.out.println("----Verify with valid Zip Code----");
        }
    }
    public void signOut(){
        linkSignout.click();
        waitForPageLoad();
        if(linkSignIn.isDisplayed()){
            System.out.println("---Logged Out Successfully---");
        }
    }
    public void registerAccount(){
        btnRegister.click();
        waitForPageLoad();
        if (txtHeader.getText().equals("My account")){
            System.out.println("---Account Created Successfully--");
        }
    }
    public void zipCode(){
        txtZipCode.clear();
        txtZipCode.sendKeys(getValue("ValidZipCode"));
        Select s2 = new Select(dropDownCountry);
        s2.selectByVisibleText("United States");
    }
    public void signInUserValid() throws InterruptedException {
        linkSignIn.click();
        waitForPageLoad();
        inputUserEmailId.clear();
        txtPassword.clear();
        inputUserEmailId.sendKeys(getValue("EmailId"));
        txtPassword.sendKeys(getValue("Password"));
        btnSignIn.click();
        waitForPageLoad();
        String s11 = txtCustomerName.getText();
        String s12 = getUserName();
        if(txtCustomerName.getText().equals(s12)){
            System.out.println("---Log in SuccessFul---");
        }
    }
    public void signInInvalidUser(){
        linkSignIn.click();
        waitForPageLoad();
        inputUserEmailId.sendKeys(getValue("EmailId"));
        txtPassword.sendKeys(getValue("InvalidPassword"));
        btnSignIn.click();
        waitForPageLoad();
        if(txtErrorMessage.isDisplayed()){
            System.out.println("---Log in Failure--- "+txtErrorMessage.getText());
        }
    }
    public String getUserName(){
        firstName = getValue("BillFirstName");
        lastName = getValue("BillLastName");
        userName = firstName+" "+lastName;
        return userName;
    }
    public void invalidCountrySelection(){
        Select s2 = new Select(dropDownCountry);
        s2.selectByVisibleText("-");
    }

}

