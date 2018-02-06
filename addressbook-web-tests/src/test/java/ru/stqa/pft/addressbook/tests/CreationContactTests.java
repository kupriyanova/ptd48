package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreationContactTests extends TestBase {

    @Test
    public void testCreationContact() {
        app.goTo().homePage();
        Contacts before = app.contact().allContacts();
        ContactData contact = new ContactData().withName("contact name")
                .withMidlename("contact midlename").withLastname("contact lastname")
                .withMobile("+7 900 000 00 00").withEmail("email@email.com").withGroup("name");

        app.contact().createContact(contact);
        app.goTo().homePage();

        Contacts after = app.contact().allContacts();
        assertThat(after.size(), equalTo(before.size() + 1));


        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }


}
