package pages;

import driverFactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {

public Driver driver;

By usernameInput = By.id("UserName");
By passwordInput = By.id("password-input");
By loginButton = By.id("btn_login");
By googleButton = By.xpath("//*[@title='Login with google']");
By forgetPasswordLink = By.xpath("//a[@href=\"/MyService/ResetPassword\"']");
By errorMessage = By.xpath("//*[@id=\"frm\"]/div[3]/span/div/ul/li");


public LoginPage(Driver driver){
    this.driver = driver;
}

public SMSPage loginWithValidCredentials(String username , String password){
    driver.element().fillField(usernameInput, username);
    driver.element().fillField(passwordInput, password);
    driver.element().click(loginButton);
    return  new SMSPage(driver);
}


public LoginPage loginWithInValidCredentials(String username , String password){
        driver.element().fillField(usernameInput, username);
        driver.element().fillField(passwordInput, password);
        driver.element().click(loginButton);
        return  this;
}

public Boolean isLoginPageDisplayed(){
    return driver.get().getCurrentUrl().equals("https://aig.ultatel.com/MyService/");
}

public  ForgetPasswordPage navigateToForgetPasswordPage(){
    driver.element().click(forgetPasswordLink);
    return new ForgetPasswordPage(driver);
}


public LoginPage hoverOnGoogleButton(){
    driver.element().hoverOnItem(googleButton);
    return this;
}











}
