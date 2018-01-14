package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name ("firstname"), contactData.getName());
        type(By.name ("middlename"), contactData.getMidlename());
        type(By.name ("lastname"), contactData.getLastname());
        type(By.name ("mobile"), contactData.getMobile());
        type(By.name ("email"),contactData.getEmail());
    }

    public void contactEdit() {
        click(By.xpath ("//a[contains(@href, 'edit')]"));
    }

    public void contactDelete() {
        click(By.xpath ("//input[@value='Delete']"));
    }
}
