import driverFactory.Driver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.FaxPage;
import pages.LoginPage;
import utilities.ExcelUtils;
import utilities.ScreenshotManager;

public class SendFax {
    Driver driver;
    String excelPath = "src/main/java/resourses/TestData.xlsx";
    ExcelUtils excel = new ExcelUtils(excelPath, "Sheet1");
    String username = excel.getStringCellData(1, 0);
    String password = excel.getStringCellData(1, 1);
    String faxSender = excel.getStringCellData(1, 2);
    String faxDestination = excel.getStringCellData(1, 3);

    @BeforeClass
    public void setUp(){
        driver = new Driver("CHROME");
        driver.get().manage().window().maximize();
        driver.get().navigate().to("https://aig.ultatel.com/MyService/Dashboard");
        driver.get().manage().deleteAllCookies();
    }
    @Test(priority = 1)
    public void userLoginSuccessfully(){
        new LoginPage(driver)
                .loginWithValidCredentials(username,password);
    }

    @Test (dependsOnMethods = "userLoginSuccessfully", priority = 2)
    public void sendFax (){
        new FaxPage (driver)
                .clickOnFaxTab()
                .checkUserNavigationToFaxPageSuccess()
                .openSelectFaxDropdown()
                .searchForFaxDestination(faxSender)
                .selectFirstFaxDropdownElement()
                .enterDestinationFaxNumber(faxDestination.replaceAll(" " , ""))
                .uploadfile()
                .clickOnSendFaxButton();

    }

    @AfterMethod
    public void checkFailure(ITestResult result){

        if (result.getStatus() == ITestResult.FAILURE){
            System.out.println("test failed");
            System.out.println("taking screen shot ....");
            ScreenshotManager.captureScreenshot(driver.get(),result.getName());

        }
    }

    @AfterClass
    public void tearDown(){
        driver.get().manage().deleteAllCookies();
        driver.quit();
    }
}
