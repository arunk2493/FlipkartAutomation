package com.project.pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class SearchResultsPage extends HomePage {
    @FindBy(xpath = "//input[@title='Search for products, brands and more']")
    public WebElement txtSearch;
    @FindBy(className = "_3O0U0u")
    public List<WebElement> searchDisplayItems;
    @FindBy(xpath = "//ul[@class='row']/li[1]/button[1]")
    public WebElement btnToCart;
    @FindBy(xpath = "//ul[@class='row']/li[1]/button[1]")
    public WebElement btnToCart2;
    @FindBy(xpath = "//button[text()='GO TO CART']")
    public WebElement btnGoToCart;
    @FindBy(xpath = "//button[text()='ADD TO CART']")
    public WebElement btnAddToCart;
    @FindBy(xpath = "//div[@class='_3O0U0u']/descendant::a[1]/div[2]/div[1]/div[1]/../../..")
    public WebElement clickProductName;
    @FindBy(xpath = "//div[@class='_1HmYoV hCUpcT']/div[2]/div[2]/div[1]/div[1]/h1/span")
    public WebElement txtProductName;
    @FindBy(xpath = "//div[@class='_1HmYoV hCUpcT']/div[2]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]")
    public WebElement txtProductPrice;
    @FindBy(xpath = "//div[@class='_3vIvU_']/div[1]/a[1]")
    public List<WebElement> txtCartProductName;
    @FindBy(xpath = "//div[@class='_3vIvU_']/span[1]")
    public List<WebElement> txtCartPriceValue;
    @FindBy(xpath = "//span[text()='Cart']/../div[1]")
    public WebElement txtCartCount;
    @FindBy(xpath = "//span[text()='Cart']/..")
    public WebElement linkCartCount;
    @FindBy(xpath = "//div[text()='Remove']")
    public List<WebElement> linkRemoveItem;


    public String searchProduct(String searchItem) throws InterruptedException {
        searchItems = getValue(searchItem);
        txtSearch.sendKeys(searchItems);
        txtSearch.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        return searchItems;
    }
    public String selectProduct(int randomProduct) throws InterruptedException {
        List<WebElement> w=null;
        WebElement w2 = null;
        WebElement w3 = null;
        if(randomProduct!=1){
            w = driver.findElements(By.xpath("//div[@class='_3O0U0u']/descendant::a[1]/div[2]/div[1]/div[1]/../../.."));
            searchRecords = w.size();
            try{
                if(randomProduct<=searchRecords){
                    String path =w.get(randomProduct).getText();
                    String prodName = path.replaceAll("\n",";");
                    System.out.println(prodName);
                    String[] prods = prodName.split(";");
                    productNameValue = prods[0].toString();
                    productPriceValue = prods[7].toString();
                    System.out.println(productPriceValue);
                    w3 = driver.findElement(By.xpath("//div[contains(text(),'"+prods[0]+"')]"));
                    scrollToElement(w3);
                    WebElement w4 = driver.findElement(By.xpath("//div[contains(text(),'"+productNameValue+"')]/../../.."));
                    w4.click();
                }
            }
            catch (Exception e){
                System.out.println("Enter the Product index within "+searchRecords+" range.");
            }
        }
        else{
            clickProductName.click();
        }
        Thread.sleep(5000);
        return searchPageProductName;
    }
    public void verifyProductPage() {
        switchTab(driver, 1);
        try {
            String s1 = txtProductName.getText();
            String s2 = txtProductPrice.getText();
            if (s1.contains(productNameValue) && s2.equals(productPriceValue)) {
                scrollToElement(btnAddToCart);
                btnAddToCart.click();
                Thread.sleep(3000);
                tests.log(Status.PASS, "Product Name and Price validation is successful");
            }else{
                tests.log(Status.FAIL,"Product Name and Price validation is not successful");
            }
        }catch (Exception e){
            tests.log(Status.FAIL,"Product Name and Price validation is not successful");
        }
    }
    public void verifyCartPage(){
        String s2=null;
        String s1=null;
       List<WebElement>  w1 = driver.findElements(By.xpath("//div[@class='_3vIvU_']/div[1]/a[1]"));
        cartSize =txtCartProductName.size();
        try{
            for(int i =0;i<cartSize;i++){
                int k =i+1;
                 s1 = driver.findElement(By.xpath("(//div[@class='_3vIvU_']/div[1]/a[1])["+k+"]")).getText();
                if(s1.equals(productNameValue)){
                    break;
                }else{
                    continue;
                }
            }
            for(int i =0;i<txtCartPriceValue.size();i++){
                int k = i+1;
                 s2 = driver.findElement(By.xpath("(//div[@class='_3vIvU_']/span[1])["+k+"]")).getText();
                if(s2.equals(productPriceValue)){
                    break;
                }else{
                    continue;
                }
            }
            Assert.assertEquals(productNameValue,s1);
            Assert.assertEquals(productPriceValue,s2);
            tests.log(Status.PASS,"Product Name and Price validation in cart is successful");
            Thread.sleep(3000);
        }catch (Exception e){
            tests.log(Status.FAIL,"Product Name and Price validation in cart is not successful");
        }
    }
    public void verifyCartItemCount()  {
        String s1 = Integer.toString(cartSize);
        try{
            closeTab(driver);
            switchTab(driver,0);
            driver.navigate().refresh();
            Thread.sleep(3000);
            cartCount = txtCartCount.getText();
            if(s1.equals(cartCount)){
                tests.log(Status.PASS,"Cart Item Count verified");
            }
            else {
                tests.log(Status.FAIL,"Cart Item Count is not verified");
            }
        }catch (Exception e){
            tests.log(Status.FAIL,"Cart Item Count is not verified");
        }
    }
    public void removeFromCart(){
        try{
            cartCount=txtCartCount.getText();
            int a = Integer.parseInt(cartCount);
            linkCartCount.click();
            Thread.sleep(3000);
            for(int i=1;i<=a;i++){
                driver.findElement(By.xpath("//div[text()='Remove']")).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath("(//div[text()='Remove'])[1]")).click();
                Thread.sleep(3000);
            }


        }catch (Exception e){
          e.printStackTrace();
        }

    }
}
