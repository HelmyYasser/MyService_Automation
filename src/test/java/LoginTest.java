import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends TestBase {

    String username = "William";
    String password = "WKafka704!";

    @Test
    public void testLoginWithValidCredentials(){
        new LoginPage(driver.get()).loginWithValidCredentials(username,password);
//        Assert.assertTrue(new LoginPage(driver.get()).loginWithValidCredentials(username,password).
//                isSMSPageDisplayed());
    }

    @Test
    public void testLoginWithInValidCredentials(){
        Assert.assertTrue(new LoginPage(driver.get()).loginWithInValidCredentials(username, "122222").isLoginPageDisplayed());
    }

//    @Test
//    public void navigateToForgetPasswordPage(){
//        Assert.assertTrue(new LoginPage(driver).navigateToForgetPasswordPage().isForgetPasswordPageDisplayed());
//    }





}
