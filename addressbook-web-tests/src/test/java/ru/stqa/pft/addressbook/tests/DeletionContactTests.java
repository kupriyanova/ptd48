package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeletionContactTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().allContacts().size() == 0) {
            if (app.group().all().size() == 0){
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("name2").withHeader("header").withFooter("group footer"));
            }
            ContactData new_contact = new ContactData().withName("contact name")
                    .withMidlename("contact midlename").withLastname("contact lastname")
                    .withMobile("+7 900 000 00 00").withEmail("email@email.com").withGroup("name");
            app.contact().createContact(new_contact);
            app.goTo().homePage();
        }
    }

    @Test
    public void testDeletionContact() throws InterruptedException {
        Contacts before = app.contact().allContacts();
        ContactData deletedContact = before.iterator().next();

        app.contact().deletion(deletedContact);
        app.goTo().homePage();
        sleep(3000); //без этого не успевает перейти на главную и считает что то другое.

        Contacts after = app.contact().allContacts();
        Assert.assertEquals(after.size(), before.size() - 1);

        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
