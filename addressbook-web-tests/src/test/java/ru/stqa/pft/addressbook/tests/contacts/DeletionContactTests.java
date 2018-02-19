package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeletionContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            if (app.group().all().size() == 0){
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("name2").withHeader("header").withFooter("group footer"));
            }
            ContactData new_contact = new ContactData().withFirstName("contact name")
                    .withMidlename("contact midlename").withLastname("contact lastname")
                    .withMobilePhone("+7 900 000 00 00").withAllEmail("email@email.com").withGroup("name");
            app.contact().createContact(new_contact);
            app.goTo().homePage();
        }
    }

    @Test
    public void testDeletionContact() throws InterruptedException {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();

        app.contact().deletion(deletedContact);
        app.goTo().homePage();
        sleep(3000); //без этого не успевает перейти на главную и считает что то другое.
        assertThat(app.contact().count(), equalTo(before.size() - 1));

        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
