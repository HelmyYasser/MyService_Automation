package pages;

import driverFactory.Driver;

public class ForgetPasswordPage {

    Driver driver;

    public ForgetPasswordPage(Driver driver){
        this.driver = driver;
    }


    public Boolean isForgetPasswordPageDisplayed(){
        return driver.get().getCurrentUrl().contains("ResetPassword");
    }



}
