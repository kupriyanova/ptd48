package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static java.lang.Thread.sleep;

public class EditContactTests extends TestBase{
    @Test
    public void testEditContact() {
        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelper().getContactCount();

        if (! app.getContactHelper().isThereAContact()) {
            if (! app.getGroupHelper().isThereAGroupName()){
                app.getNavigationHelper().gotoGroupPage();
                app.getGroupHelper().createGroup(new GroupData("name", "group header", "group footer"));
            }
            app.getNavigationHelper().gotoCreationContactPage();
            app.getContactHelper().fillContactForm(new ContactData("contact name", "contact midlename",
                    "contact lastname", "+7 900 000 00 00", "email@email.com", "name"), true);
            app.getGroupHelper().clickSubmit();
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().contactEdit(0);
        app.getContactHelper().fillContactForm(new ContactData("new name", "new midlename",
                "new lastname", "+7 900 000 00 00", "email@email.com", null), false);
        app.getContactHelper().clickUpdate();
        app.getNavigationHelper().gotoHomePage();

        int after = app.getContactHelper().getContactCount();
        if (before == 0) {
            Assert.assertEquals(after, before + 1);
        } else {
            Assert.assertEquals(after, before);
        }
    }
}
