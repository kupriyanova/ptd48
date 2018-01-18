package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.ApplicationManeger;
import org.openqa.selenium.remote.BrowserType;

public class TestBase {

    protected final ApplicationManeger app = new ApplicationManeger(BrowserType.FIREFOX);

    @BeforeMethod
    public void setUp(){
        app.init();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }


}
