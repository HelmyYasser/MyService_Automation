package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SafariDriverFactory extends DriverAbstract {
    @Override
    public WebDriver startDriver() {
        driver = new SafariDriver();
        return driver;
    }
}
