package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.NoSuchElementException;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, Boolean creation) {
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

    public void selectContact(int index) {
        wd.findElements(By.name ("selected[]")).get(index).click();
    }

    public void contactEdit(int index) {
        wd.findElements(By.xpath ("//table[@id='maintable']//a[contains(@href, 'edit')]")).get(index).click();
    }

    public void contactDelete() {
        click(By.xpath ("//input[@value='Delete']"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//tr[@name='entry']"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }
}