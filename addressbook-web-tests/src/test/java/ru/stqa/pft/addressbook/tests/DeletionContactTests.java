package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class DeletionContactTests extends TestBase{
    @Test
    public void testDeletionContact() throws InterruptedException {
        app.getnavigationHelper().returnHomePage();
        sleep(1000);
        app.getGroupHelper().clickSelect();
        app.getContactHelper().contactDelete();
        app.getContactHelper().alertOk();
        app.getnavigationHelper().returnHomePage();
    }
}
