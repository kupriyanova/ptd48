package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class CreationGroupTests extends TestBase {

    @Test
    public void testCreationGroup() {
        app.getnavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("name", "header", "footer"));
        app.getGroupHelper().clickSubmit();
        app.getnavigationHelper().returnGroupPage();
    }
}
