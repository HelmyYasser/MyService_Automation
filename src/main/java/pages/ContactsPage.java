package pages;

import driverFactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ContactsPage {


   Driver driver;
   By addNewPersonalBtn = By.id("Addpersonal");
   By addContactLabel = By.id("addEditDialogLabel");


    public  ContactsPage (Driver driver){
    this.driver = driver;
    }


    public AddContactPage clickContactBtn(){
        driver.element().click(addNewPersonalBtn);
        Assert.assertTrue(driver.element().getTextOf(addContactLabel).contains("Add Contact"));
        return new AddContactPage(driver);
    }

//    public AddContactPage checkAddContactFormOpenSuccessfully (){
//        Assert.assertTrue(driver.element().getTextOf(addContactLabel).contains("Add Contact"));
//        return new AddContactPage(driver);
//    }


}
