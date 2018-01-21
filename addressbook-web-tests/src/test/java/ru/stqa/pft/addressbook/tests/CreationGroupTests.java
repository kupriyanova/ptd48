package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class CreationGroupTests extends TestBase {

    @Test
    public void testCreationGroup() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("name", "header", "group footer"));
    }
}
