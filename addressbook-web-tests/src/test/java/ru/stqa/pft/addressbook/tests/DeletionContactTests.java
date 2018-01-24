package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static java.lang.Thread.sleep;

public class DeletionContactTests extends TestBase{
    @Test
    public void testDeletionContact() throws InterruptedException {
        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelper().getContactCount();
        System.out.println("before = " + before);

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
        app.getContactHelper().selectContact(before - 1);
        app.getContactHelper().contactDelete();
        app.getContactHelper().alertOk();
        app.getNavigationHelper().gotoHomePage();
        sleep(2000); //без этого не успевает перейти на главную и считает что то другое.

        int after = app.getContactHelper().getContactCount();
        System.out.println("After = " + after);
        if (before == 0) {
            Assert.assertEquals(after, before);
        } else {
            Assert.assertEquals(after, before - 1);
        }
    }
}
