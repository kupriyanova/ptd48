package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class HelperBase {
    private WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String name) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(name);
    }

    public void clickSubmit() {
        click(By.name ("submit"));
    }
    public void clickUpdate() {
        click(By.name ("update"));
    }

    public void alertOk(){
        wd.switchTo().alert().accept();
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
