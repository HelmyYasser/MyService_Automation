import driverFactory.Driver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.ScreenshotManager;

public class TestBase {

 //public Driver driver;
 public ThreadLocal<Driver> driver;

    @BeforeMethod
    @Parameters(value = {"browserName"})
    public void setUp(@Optional("CHROME") String browserName){
        driver = new ThreadLocal<>();
        driver.set(new Driver(browserName));
        //driver = new Driver(browserName); // Initialize the driver for each thread
        driver.get().get().manage().window().maximize();
        driver.get().get().navigate().to("https://aig.ultatel.com/MyService/Dashboard");
        driver.get().get().manage().deleteAllCookies();
    }



    @AfterMethod
    public void tearDown(){
       driver.get().get().manage().deleteAllCookies();
        // Close the driver
        if (driver.get() != null) {
            driver.get().quit();
        }
    }

}
