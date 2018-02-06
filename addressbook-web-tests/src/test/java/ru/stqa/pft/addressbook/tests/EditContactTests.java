package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EditContactTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (! app.contact().isThereAContact()) {
            if (! app.group().isThereAGroupName()){
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
    public void testEditContact() {
        app.goTo().homePage();

        Contacts before = app.contact().allContacts();
        ContactData editableContact = before.iterator().next();
        ContactData contact = new ContactData().withId(editableContact.getId()).withName("contact name")
                .withMidlename("contact midlename").withLastname("contact lastname")
                .withMobile("+7 900 000 00 00").withEmail("email@email.com").withGroup("name");

        app.contact().editContact(editableContact, contact);
        app.goTo().homePage();

        Contacts after = app.contact().allContacts();
        Assert.assertEquals(after, before);

        assertThat(after, equalTo(before.without(editableContact).withAdded(contact)));
    }
}
