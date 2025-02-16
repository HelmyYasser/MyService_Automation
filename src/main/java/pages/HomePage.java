package pages;
import driverFactory.Driver;
import org.openqa.selenium.*;



public class HomePage {

    Driver driver;
    By contactsOption = By.xpath("//a[@href='/MyService/Contacts']");

    public HomePage (Driver driver){
        this.driver=driver;
    }


    public ContactsPage clickOnContactLink() {
       driver.element().click(contactsOption);
       return new ContactsPage(driver);
    }

    public boolean isDashoardPageDisplayed(){
        return driver.get().getCurrentUrl().contains("/Dashboard");
    }







}