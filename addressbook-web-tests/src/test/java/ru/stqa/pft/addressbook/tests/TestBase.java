package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManeger;
import org.openqa.selenium.remote.BrowserType;

public class TestBase {

    protected static final ApplicationManeger app = new ApplicationManeger(BrowserType.FIREFOX);

    @BeforeSuite
    public void setUp(){
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }


}
