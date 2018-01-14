package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManeger {

    private WebDriver wd;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;

    public void init() {
        System.setProperty("webdriver.gecko.driver", "/Applications/geckodriver");
        wd = new FirefoxDriver();
        wd.get("http://localhost/addressbook/index.php");
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }
    public ContactHelper getContactHelper() {
        return contactHelper;
    }
    public NavigationHelper getnavigationHelper() {
        return navigationHelper;
    }
    public SessionHelper getsessionHelper() {
        return sessionHelper;
    }
}
