package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class CreationContactTests extends TestBase {

    @Test
    public void testCreationContact() {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getNavigationHelper().gotoCreationContactPage();
        ContactData contact = new ContactData("contact name", "contact midlename",
                "contact lastname", "+7 900 000 00 00", "email@email.com", "name");
        app.getContactHelper().fillContactForm(contact, true);
        app.getGroupHelper().clickSubmit();
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        System.out.println("Before = " + before);
        after.sort(byId);
        System.out.println("after = " + after);
        Assert.assertEquals(before, after);
    }
}
