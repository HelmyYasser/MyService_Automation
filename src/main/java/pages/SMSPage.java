package pages;

import driverFactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SMSPage {

    Driver driver;
    By toInput = By.xpath("//*[@id=\"otherParty_toinput\"]/div[2]/div/div[1]/span/span[1]/span/ul/li/input");
    By typeMessageInput = By.xpath("//*[@id=\"drop_zone\"]/div/div[1]");
    By sendButton = By.name("btn_SendNewSMS");
    By lastMessage = By.xpath("(//ul[@class='chat']/li)[last()]");
    By lastMessageText = By.xpath("(//ul[@class='chat']/li)[last()]//p");
    By lastDeleteButton = By.xpath("(//ul[@class='chat']/li)[last()]//a[@title='Delete Message']");


    public SMSPage (Driver driver){
        this.driver = driver;
    }

    public boolean isSMSPageDisplayed(){
        return driver.get().getCurrentUrl().contains("/SMS");
    }
    public SMSPage sendSMS(String DID, String message){
        driver.element().fillField(toInput, DID);
        driver.element().fillField(typeMessageInput,message);
        driver.element().click(sendButton);
        return this;
    }


    public Boolean isSentMessageDisplayed(String message){
        return driver.element().getTextOf(lastMessageText).contains(message);
    }

    public SMSPage deleteMessage(){
        driver.element().click(lastDeleteButton);
        return this;
    }
}
