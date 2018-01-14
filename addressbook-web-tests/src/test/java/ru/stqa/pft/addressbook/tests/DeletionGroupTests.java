package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class DeletionGroupTests extends TestBase {

    @Test
    public void testDeletionGroup() {
        app.getnavigationHelper().gotoGroupPage();
        app.getGroupHelper().clickSelect();
        app.getGroupHelper().clickDelete();
        app.getnavigationHelper().returnGroupPage();
    }
}
