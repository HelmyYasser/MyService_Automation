package pages;

import driverFactory.Driver;
import org.openqa.selenium.By;
import org.testng.Assert;


public class AddContactPage {

    Driver driver;

    public AddContactPage(Driver driver) {
        this.driver = driver;
    }


    By firstNameTxtBox = By.id("txtFirstName");
    By lastNameTxtBox = By.id("txtLastName");
    By webPageTxtBox = By.id("txtWebPage");
    By primaryPhoneTxtBox = By.id("txtPrimaryPhone");
    By homePhoneTxtBox = By.id("txtHomePhone");
    By mobilePhoneTxtBox = By.id("txtMobilePhone");
    By extensionTxtBox = By.id("txtExtension");
    By businessPhoneTxtBox = By.id("txtBusinessPhone");
    By companyTxtBox = By.id("txtCompany");
    By faxTxtBox = By.id("txtBusinessFax");
    By emailTxtBox = By.id("txtEmail");
    By noteTxtBox = By.id("NoteArea");
    By saveBtn = By.id("btn btn-success");
    By cancelBtn = By.id("btn btn-outline-secondary");


    public AddContactPage addContactForm(String firstName) {
        driver.element().fillField(firstNameTxtBox, firstName);
        driver.element().click(saveBtn);
        return this;
    }

    public AddContactPage addContactForm(String firstNameTxt, String lastNameTxt, String webPageTxt, String primaryPhoneTxt, String homePhoneTxt, String mobilePhoneTxt, String extensionTxt, String businessPhoneTxt, String companyTxt, String faxTxt, String emailTxt, String noteTxt) {
        driver.element().fillField(firstNameTxtBox, firstNameTxt);
        driver.element().fillField(lastNameTxtBox, lastNameTxt);
        driver.element().fillField(webPageTxtBox, webPageTxt);
        driver.element().fillField(primaryPhoneTxtBox, primaryPhoneTxt);
        driver.element().fillField(homePhoneTxtBox, homePhoneTxt);
        driver.element().fillField(mobilePhoneTxtBox, mobilePhoneTxt);
        driver.element().fillField(extensionTxtBox, extensionTxt);
        driver.element().fillField(businessPhoneTxtBox, businessPhoneTxt);
        driver.element().fillField(companyTxtBox, companyTxt);
        driver.element().fillField(faxTxtBox, faxTxt);
        driver.element().fillField(emailTxtBox, emailTxt);
        driver.element().fillField(noteTxtBox, noteTxt);
        driver.element().click(saveBtn);

     return this;
    }




}
