package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class CreationContactTests extends TestBase {

    @Test
    public void testCreationContact() {
        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelper().getContactCount();
        System.out.println("Before = " + before);

        app.getNavigationHelper().gotoCreationContactPage();
        app.getContactHelper().fillContactForm(new ContactData("contact name", "contact midlename",
                "contact lastname", "+7 900 000 00 00", "email@email.com", "name"), true);
        app.getGroupHelper().clickSubmit();

        app.getNavigationHelper().gotoHomePage();

        int after = app.getContactHelper().getContactCount();
        System.out.println("After = " + after);
        Assert.assertEquals(after, before + 1);
    }
}
