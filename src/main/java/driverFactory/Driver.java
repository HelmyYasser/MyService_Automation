package driverFactory;

import browserActions.BrowserActions;
import elementActions.ElementActions;
import listeners.webdriver.WebDriverListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class Driver {

   // public WebDriver driver;
    public ThreadLocal<WebDriver> driver;

    public Driver(String driverType) {
        //   driver = getDriver(browserType).startDriver();


        WebDriver undecoratedDriver = getDriver(driverType).startDriver();
        assert undecoratedDriver != null;

        driver = new ThreadLocal<>();
        driver.set( new EventFiringDecorator<>(org.openqa.selenium.WebDriver.class,
                new WebDriverListener(undecoratedDriver))
                .decorate(undecoratedDriver));


    }


    private static DriverAbstract getDriver(String driver) {

        switch (driver) {
            case "CHROME": {
                return new ChromeDriverFactory();
            }
            case "FIREFOX": {
                return new FireFoxDriverFactory();
            }
            case "EDGE": {
                return new EdgeDriverFactory();
            }
            case "SAFARI":
                return new SafariDriverFactory();
            default: {
                throw new IllegalStateException("Unexpected value: " + driver);
            }
        }
    }

    public WebDriver get() {
        return driver.get();
    }

    public void quit() {
        driver.get().quit();
    }

    public ElementActions element() {
        return new ElementActions(driver.get());
    }

    public BrowserActions browser() {
        return new BrowserActions(driver.get());
    }

}
