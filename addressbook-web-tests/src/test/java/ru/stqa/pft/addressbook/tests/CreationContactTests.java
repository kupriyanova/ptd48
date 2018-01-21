package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class CreationContactTests extends TestBase {

    @Test
    public void testCreationContact() {
        app.getNavigationHelper().gotoCreationContactPage();
        app.getContactHelper().fillContactForm(new ContactData("contact name", "contact midlename",
                "contact lastname", "+7 900 000 00 00", "email@email.com", "name"), true);
        app.getGroupHelper().clickSubmit();
    }
}
