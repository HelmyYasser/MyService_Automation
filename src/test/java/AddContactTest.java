import org.testng.annotations.Test;
import pages.AddContactPage;
import pages.ContactsPage;

public class AddContactTest  extends TestBase {

    @Test
    public void addNewPersonalContact() {
       new AddContactPage(driver.get())
               .addContactForm("Rana");
    }
}
