package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static java.lang.Thread.sleep;

public class EditContactTests extends TestBase{
    @Test
    public void testEditContact() throws InterruptedException {
        app.getnavigationHelper().returnHomePage();
        sleep(1000);
        app.getContactHelper().contactEdit();
        app.getContactHelper().fillContactForm(new ContactData("new name", "new midlename",
                "new lastname", "+7 900 000 00 00", "email@email.com"));
        app.getContactHelper().clickSubmit();
        app.getnavigationHelper().returnHomePage();
    }
}
