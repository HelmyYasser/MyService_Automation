import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class HomeTest extends TestBase {

    LoginPage loginPage;
    String username = "William";
    String password = "WKafka704!";

    @Test
    public void selectContactsFromMenu() {
        loginPage = new LoginPage(driver.get());
        loginPage.loginWithValidCredentials(username, password);
        new HomePage(driver.get()).clickOnContactLink();
    }

}
