package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;


import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (! app.contact().isThereAContact()) {
            if (! app.group().isThereAGroupName()){
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("name2").withHeader("header").withFooter("group footer"));
            }
            ContactData new_contact = new ContactData().withFirstName("contact name")
                    .withMidlename("contact midlename").withLastname("contact lastname")
                    .withHomePhone("112233").withMobilePhone("+7 900 000 00 00").withWorkPhone("332211")
                    .withFirstEmail("email@email.com").withGroup("name");
            app.contact().createContact(new_contact);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactEmail() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        Assert.assertEquals(contact.getAllEmail(), mergeEmail(contactInfoFromEditForm));
        //assertThat(contact.getAllEmail(), equals(mergeEmail(contactInfoFromEditForm)));
        //MatcherAssert считает, что это разные строки.
        //Тогда как assert проверку проходит.
    }

    private String mergeEmail(ContactData contact) {
        return Arrays.asList(contact.getFirstEmail(), contact.getSecondEmail(), contact.getThirdEmail())
                .stream().filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
    }
}
