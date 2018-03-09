package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.*;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fill(ContactData contactData, Boolean creation) {
        type(By.name ("firstname"), contactData.getName());
        type(By.name ("middlename"), contactData.getMidlename());
        type(By.name ("lastname"), contactData.getLastname());
        type(By.name ("home"), contactData.getHomePhone());
        type(By.name ("mobile"), contactData.getMobilePhone());
        type(By.name ("work"), contactData.getWorkPhone());
        type(By.name ("email"),contactData.getFirstEmail());
        //attach(By.name ("photo"), contactData.getPhoto());
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
        contactCach = null;
    }

    public void clickEditById(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id="+ id +"']")).click();
    }

    public void editContact(ContactData editable_contact, ContactData contact) {
        clickEditById(editable_contact.getId());
        fill(contact, false);
        clickUpdate();
        contactCach = null;
    }
    public void delete() {
        click(By.xpath ("//input[@value='Delete']"));
    }

    public void deletion(ContactData selectedContact) {
        selectContactById(selectedContact.getId());
        delete();
        alertOk();
        contactCach = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//tr[@name='entry']"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCach = null;

    public Contacts all() {
        if (contactCach != null) {
            return new Contacts(contactCach);
        }
        contactCach = new Contacts();
        List<WebElement> rows = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            String address = cells.get(3).getText();
            String allEmail = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastname(lastName)
                    .withAddress(address).withAllEmail(allEmail).withAllPhones(allPhones);
            contactCach.add(contact);
        }
        return contactCach;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        clickEditById(contact.getId());
        String name = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();

        return new ContactData()
                .withId(contact.getId()).withFirstName(name).withLastname(lastname).withAddress(address)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withFirstEmail(email).withSecondEmail(email2).withThirdEmail(email3);
    }
}