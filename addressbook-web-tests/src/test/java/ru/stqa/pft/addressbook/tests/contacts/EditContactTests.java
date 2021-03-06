package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Set;

import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EditContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            ContactData new_contact = new ContactData().withFirstName("contact name")
                    .withMidlename("contact midlename").withLastname("contact lastname")
                    .withMobilePhone("+7 900 000 00 00").withAllEmail("email@email.com").withGroup("name");
            app.contact().createContact(new_contact);
            app.goTo().homePage();
        }
    }

    @Test
    public void testEditContact() {

        Contacts before = app.db().contacts();
        ContactData editableContact = before.iterator().next();
        ContactData contact = new ContactData().withId(editableContact.getId()).withFirstName("contact_name")
                .withMidlename("contact_midlename").withLastname("contact_lastname")
                .withMobilePhone("+7(900)000-00-00").withAllEmail("email@email.com");

        app.goTo().homePage();
        app.contact().editContact(editableContact, contact);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size()));

        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(editableContact).withAdded(contact)));
    }
}
