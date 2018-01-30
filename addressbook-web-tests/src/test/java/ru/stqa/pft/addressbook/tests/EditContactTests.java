package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

import static java.lang.Thread.sleep;

public class EditContactTests extends TestBase{
    @Test
    public void testEditContact() {
        app.getNavigationHelper().gotoHomePage();

        if (! app.getContactHelper().isThereAContact()) {
            if (! app.getGroupHelper().isThereAGroupName()){
                app.getNavigationHelper().gotoGroupPage();
                app.getGroupHelper().createGroup(new GroupData("name", "group header", "group footer"));
            }
            app.getNavigationHelper().gotoCreationContactPage();
            ContactData new_contact = new ContactData("contact name", "contact midlename",
                    "contact lastname", "+7 900 000 00 00", "email@email.com", "name");
            app.getContactHelper().fillContactForm(new_contact, true);
            app.getGroupHelper().clickSubmit();
            app.getNavigationHelper().gotoHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        System.out.println("Before: " + before);
        app.getContactHelper().contactEdit(before.size() - 1);
        ContactData edit_contact = new ContactData("new name", "new midlename",
                "new lastname", "+7 900 000 00 00", "email@email.com", null);
        app.getContactHelper().fillContactForm(edit_contact, false);
        app.getContactHelper().clickUpdate();
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        System.out.println("After: " + after);
        Assert.assertEquals(after, before);

        before.remove(before.size() - 1);
        before.add(edit_contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
