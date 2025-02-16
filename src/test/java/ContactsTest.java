
import org.testng.annotations.Test;
import pages.AddContactPage;
import pages.ContactsPage;

public class ContactsTest extends TestBase {


    @Test
    public void openAddNewPersonalContactForm() {
        new ContactsPage(driver.get())
                .clickContactBtn();
    }
}




