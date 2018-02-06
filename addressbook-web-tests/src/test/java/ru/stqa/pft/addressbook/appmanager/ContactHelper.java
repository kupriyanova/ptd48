package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.*;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fill(ContactData contactData, Boolean creation) {
        type(By.name ("firstname"), contactData.getName());
        type(By.name ("middlename"), contactData.getMidlename());
        type(By.name ("lastname"), contactData.getLastname());
        type(By.name ("mobile"), contactData.getMobile());
        type(By.name ("email"),contactData.getEmail());

        if(creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void goToCreationContactPage() {
        click(By.linkText ("add new"));
    }

    public void createContact(ContactData contact) {
        goToCreationContactPage();
        fill(contact, true);
        clickSubmit();
    }

    public void clickEditButton(int id) {
        wd.findElement(By.xpath ("//a[@href, 'edit.php?id=" + id + "']")).click();
    }

    public void editContact(ContactData editable_contact, ContactData contact) {
        clickEditButton(contact.getId());
        fill(editable_contact, false);
        clickUpdate();
    }
    public void delete() {
        click(By.xpath ("//input[@value='Delete']"));
    }

    public void deletion(ContactData selectedContact) {
        selectContactById(selectedContact.getId());
        delete();
        alertOk();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//tr[@name='entry']"));
    }

    public Contacts allContacts() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstName = element.findElement(By.xpath("./td[3]")).getText();
            String lastName = element.findElement(By.xpath("./td[2]")).getText();
            ContactData contact = new ContactData().withId(id).withName(firstName).withLastname(lastName);
            contacts.add(contact);
        }
        return contacts;
    }
}