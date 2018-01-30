package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

import static java.lang.Thread.sleep;

public class DeletionContactTests extends TestBase{
    @Test
    public void testDeletionContact() throws InterruptedException {
        app.getNavigationHelper().gotoHomePage();

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
        List<ContactData> before = app.getContactHelper().getContactList();
        System.out.println("before = " + before);
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().contactDelete();
        app.getContactHelper().alertOk();
        app.getNavigationHelper().gotoHomePage();
        sleep(3000); //без этого не успевает перейти на главную и считает что то другое.

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(after, before);
    }
}
