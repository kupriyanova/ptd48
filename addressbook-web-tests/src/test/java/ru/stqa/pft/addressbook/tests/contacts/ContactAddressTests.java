package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (! app.contact().isThereAContact()) {
            if (! app.group().isThereAGroupName()){
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("name2").withHeader("header").withFooter("group footer"));
            }
            ContactData new_contact = new ContactData().withFirstName("contact name")
                    .withMidlename("contact midlename").withLastname("contact lastname").withAddress("address_text")
                    .withHomePhone("112233").withMobilePhone("+7 900 000 00 00").withWorkPhone("332211")
                    .withAllEmail("email@email.com").withGroup("name");
            app.contact().createContact(new_contact);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        System.out.println(contact.getAddress());
        Assert.assertEquals(contact.getAddress(), contactInfoFromEditForm.getAddress());
    }
}
