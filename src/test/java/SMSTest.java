import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SMSPage;

public class SMSTest extends TestBase {

    String username = "Rana.hesham@ultatel.com";
    String password = "Rona@2982000";


    @BeforeClass
    public void login(){
        new LoginPage(driver.get())
                .loginWithValidCredentials(username,password)
                .isSMSPageDisplayed();
    }
    @Test
    public void sendSMS() {
          new SMSPage(driver.get())
                  .sendSMS("5719913003", "Hello from automation ");
        Assert.assertTrue(new SMSPage(driver.get()).isSMSPageDisplayed());
    }

    @Test
    public void sendAgain(){
        new SMSPage(driver.get())
                .sendSMS("5716500218", "Hello from automation 2 ");
    }


}
