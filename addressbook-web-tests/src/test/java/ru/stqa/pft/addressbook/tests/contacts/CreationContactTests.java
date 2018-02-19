package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreationContactTests extends TestBase {

    @Test
    public void testCreationContact() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstName("contact name")
                .withMidlename("contact midlename").withLastname("contact lastname")
                .withMobilePhone("+7 900 000 00 00").withAllEmail("email@email.com").withGroup("name");

        app.contact().createContact(contact);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));

        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }


}
