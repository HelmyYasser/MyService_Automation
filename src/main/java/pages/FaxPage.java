package pages;

import driverFactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class FaxPage {

    Driver driver;

    By faxTab = By.cssSelector("a[href='/MyService/Fax']");
    By selectYourFax  = By.id("ddl_extension_chosen");
    By firstFaxDropdownElement = By.xpath("//*[@id=\"ddl_extension_chosen\"]/div/ul/li[1]");
    By faxDDLSearch = By.xpath("//input[@class=\"chosen-search-input\"] ");
    By DestinationFaxNumber = By.xpath("//input[@id=\"faxdestination\"]");
    By fileToUploadButton = By.id("fileToUpload-1");
    By sendfaxButton = By.id("btn_sendfax");
    WebDriverWait wait;


    public  FaxPage (Driver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver.get(), Duration.ofSeconds(10));
    }


    public FaxPage clickOnFaxTab(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockOverlay")));
        wait.until(ExpectedConditions.elementToBeClickable(faxTab));
        driver.element().click(faxTab);
        return this;
    }
    public FaxPage checkUserNavigationToFaxPageSuccess(){
        Assert.assertTrue(driver.get().getCurrentUrl().contains("Fax"));
        return this;
    }

    public FaxPage openSelectFaxDropdown(){
        driver.element().click(selectYourFax);
        return this;
    }
    public FaxPage searchForFaxDestination(String text){
        driver.element().fillField(faxDDLSearch, text);
        return this;
    }

    public FaxPage selectFirstFaxDropdownElement() {
        driver.element().click(firstFaxDropdownElement);
        return this;
    }
    public FaxPage enterDestinationFaxNumber (String text) {
        wait.until(ExpectedConditions.elementToBeClickable(DestinationFaxNumber));
        driver.element().click(DestinationFaxNumber);
        driver.element().fillField(DestinationFaxNumber,text);
        return this;
    }

    public FaxPage uploadfile () {
        driver.element().uploadfile(fileToUploadButton, "C:\\Users\\HelmyYasserHelmy\\Downloads\\file.pdf");
        return this;
    }

    public FaxPage clickOnSendFaxButton () {
        driver.element().click(sendfaxButton);
        return this;
    }




}
