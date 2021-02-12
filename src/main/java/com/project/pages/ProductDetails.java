package com.project.pages;

import com.sun.tracing.dtrace.FunctionName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ProductDetails extends UserDetails {
    @FindBy(id = "search_query_top")
    public WebElement inputSearchProduct;
    @FindBy(name = "submit_search")
    public WebElement btnSearchProduct;
    @FindBy(className = "product-count")
    public WebElement txtProductCount;
    @FindBy(xpath = "//a[@class='open-comment-form']")
    public WebElement btnReview;
    @FindBy(className = "page-subheading")
    public WebElement txtWriteReview;
    @FindBy(name = "title")
    public WebElement txtReviewTitle;
    @FindBy(name = "content")
    public WebElement txtReviewComment;
    @FindBy(name="submitMessage")
    public WebElement btnSubmitReview;
    @FindBy(xpath = "//*[@class='fancybox-inner']/p")
    public WebElement txtReviewConfirmation;
    @FindBy(xpath = "//*[@class='fancybox-inner']/p[2]/button")
    public WebElement btnOK;
    @FindBy(xpath = "//a[@title='Cancel Rating']")
    public WebElement iconCancelRating;
    @FindBy(xpath = "//i[@class='icon-facebook']/..")
    public WebElement iconFacebook;
    @FindBy(xpath = "//i[@class='icon-google-plus']/..")
    public WebElement iconGmail;
    @FindBy(xpath = "//i[@class='icon-pinterest']/..")
    public WebElement iconPintrest;
    @FindBy(xpath = "//a[@title='Add to cart']")
    public WebElement btnAddToCart;
    @FindBy(xpath = "//span[@title='Close window']/../h2")
    public WebElement txtProductConfirm;
    @FindBy(xpath = "//span[@title='Continue shopping']")
    public WebElement btnContinueShopping;
    @FindBy(xpath = "//span[@title='Proceed to checkout']")
    public WebElement btnProceedCheckOut;
    @FindBy(xpath = "//a[@title='View my shopping cart']")
    public WebElement btnCart;
    //View my shopping cart
    @FindBy(xpath = "//span[@class='heading-counter']")
    public WebElement txtSearchCount;
    @FindBy(xpath = "//a[@title='Faded Short Sleeve T-shirts']")
    public WebElement txtSearchedProductName;
    @FindBy(xpath = "//div[@class='block products_block']/div/ul/li")
    public List<WebElement> listManufacturers;
    @FindBy(name = "Submit")
    public WebElement btnAddCart;
    @FindBy(id="summary_products_quantity")
    public WebElement txtProductSummary;
    @FindBy(name = "processAddress")
    public WebElement btnSubmitFromAddress;
    @FindBy(id = "cgv")
    public WebElement checkBoxTerms;
    @FindBy(name = "processCarrier")
    public WebElement btnSubmitFromShipping;
    //Pay by bank wire
    @FindBy(xpath = "//*[@class='page-subheading]")
    public WebElement txtPaymentMethod;
    @FindBy(xpath = "//*[@id='cart_navigation']/button")
    public WebElement btnSubmitOrder;
    @FindBy(xpath = "//*[@class='page-heading']/following::div/p/strong")
    public WebElement txtOrderConfirmation;
    @FindBy(xpath = "//a[@title='Back to orders']")
    public WebElement btnOrderSummaryPage;
    @FindBy(xpath = "//table[@id='order-list']/thead/tr[1]/th")
    public List<WebElement> listOrderSummaryHeader;
    @FindBy(xpath = "//table[@id='order-list']/tbody/tr")
    public List<WebElement> listOrderSummaryDetails;
    @FindBy(xpath = "//div[@class='block products_block']/div/ul/li[1]/div/h5/a[1]")
    public WebElement txtProduct;
    @FindBy(xpath = "//*[@id='add_to_cart']/button")
    public WebElement btnAddtocart;
    @FindBy(xpath = "//*[@class='clearfix']/div/span")
    public WebElement btnClose;



    public void searchProduct() throws InterruptedException {
        inputSearchProduct.sendKeys(getValue("ProductSearch"));
        btnSearchProduct.click();
        waitForPageLoad();
    }
    public void verifySearchCount(){
        String searchCount = txtSearchCount.getText();
        String searchCountActual = searchCount.trim();
        String searchCountExpected = getValue("SearchCount")+" "+"result has been found.";
        Assert.assertEquals(searchCountActual,searchCountExpected);
    }
    public void verifySearchedProduct(){
        String productName = driver.findElement(By.xpath("//a[@class='product_img_link']")).getAttribute("title");
        String prodNameActual = productName.trim();
        String prodNameExpected = getValue("Productname");
        Assert.assertEquals(prodNameActual,prodNameExpected);
    }
    public void selectManufacturer() {
        driver.findElement(By.xpath("//div[@class='block products_block']/div/ul/li[1]/div/h5/a[1]")).click();
        btnAddtocart.click();
        if (txtProductConfirm.getText().equals(getValue("ProductConfirm"))) {
            System.out.println("---Product added to cart successfully");
        }
    }
    public void selectManufacturer2() {
        driver.findElement(By.xpath("//div[@class='block products_block']/div/ul/li[2]/div/h5/a[1]")).click();
        btnAddtocart.click();
        if (txtProductConfirm.getText().equals(getValue("ProductConfirm"))) {
            System.out.println("---Product added to cart successfully");
        }
    }
    public void selectManufacturer3() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='block products_block']/div/ul/li[3]/div/h5/a[1]")).click();
        btnAddtocart.click();
        if (txtProductConfirm.getText().equals(getValue("ProductConfirm"))) {
            System.out.println("---Product added to cart successfully");
        }
        btnCart.click();
        waitForPageLoad();
    }
    public void proceedToCheckOut() throws InterruptedException {
        btnProceedCheckOut.click();
        waitForPageLoad();
        btnSubmitFromAddress.click();
        waitForPageLoad();
        checkBoxTerms.click();
        btnSubmitFromShipping.click();
        String paymentMethod = getValue("PaymentMethodType");
        driver.findElement(By.xpath("//a[@title='"+paymentMethod+"']")).click();
        waitForPageLoad();
        String s1 = txtPaymentMethod.getText();
        String s3 = s1.trim();//Bank-wire payment.
        Assert.assertEquals(s3,getValue("PaymentMethod"));
        btnSubmitOrder.click();
        waitForPageLoad();
        String s2 = txtOrderConfirmation.getText();
        Assert.assertEquals(s2,getValue("OrderConfirmation"));//Your order on My Store is complete.
        btnOrderSummaryPage.click();
        waitForPageLoad();
    }
    public void verifyOrderDetails(){
        int a1 = listOrderSummaryHeader.size();
        int b1 = listOrderSummaryDetails.size();
        for(int j=1;j<a1;j++){
            String header = driver.findElement(By.xpath("//table[@id='order-list']/thead/tr[1]/th['"+j+"']")).getText();
            String details = driver.findElement(By.xpath("//table[@id='order-list']/tbody/tr[1]/td['"+j+"']")).getText();
            System.out.println("---Order Details---");
            System.out.println(header+" :"+details);
        }
    }
   /* public void shareToFacebook(){
        txtProduct.click();
        waitForPageLoad();
        iconFacebook.click();
        waitForPageLoad();
        switchWindows(driver);
        if(newURL.contains("Facebook")){
            System.out.println("Opened FaceBook");
            closeWindows(driver);

        }
        else{
            System.out.println("Not Opened");
        }
    }*/
    /*public void shareToGooglePlus() throws InterruptedException {
        txtProduct.click();
        waitForPageLoad();
        iconGmail.click();
        waitForPageLoad();
        switchWindows(driver);
        if(newURL.contains("Google")){
            System.out.println("Opened Google");
            closeWindows(driver);
        }
        else{
            System.out.println("Not Opened");
        }
    }
    public void shareToPinterest(){
        txtProduct.click();
        waitForPageLoad();
        iconPintrest.click();
        waitForPageLoad();
        switchWindows(driver);
        if(newURL.contains("Pinterest")){
            System.out.println("Opened Pinterest");
            closeWindows(driver);
        }
        else{
            System.out.println("Not Opened");
        }
    }
    public void writeReview(){
        txtProduct.click();
        waitForPageLoad();
        btnReview.click();
        waitForPageLoad();
        System.out.println("====Provide Rating====");
        iconCancelRating.click();
        driver.findElement(By.xpath("//a[@title='"+getValue("Rating")+"']")).click();
        txtReviewTitle.sendKeys(getValue("ReviewTitle"));
        txtReviewComment.sendKeys(getValue("ReviewComment"));
        btnSubmitReview.click();
        waitForPageLoad();
        String s4 = txtReviewConfirmation.getText();//Your comment has been added and will be available once approved by a moderator
        Assert.assertEquals(s4,getValue("ReviewConfirmation"));
        btnSubmitReview.click();
    }*/
}
