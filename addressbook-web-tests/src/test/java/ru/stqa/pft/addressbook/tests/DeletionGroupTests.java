package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class DeletionGroupTests extends TestBase {

    @Test
    public void testDeletionGroup() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("name", "header", "group footer"));
        }
        app.getGroupHelper().clickSelect();
        app.getGroupHelper().clickDelete();
        app.getGroupHelper().returnGroupPage();
    }
}
