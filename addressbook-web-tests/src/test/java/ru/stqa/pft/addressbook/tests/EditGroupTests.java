package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class EditGroupTests extends TestBase {
    @Test
    public void testModificationGroup() {
        app.getnavigationHelper().gotoGroupPage();
        app.getGroupHelper().clickSelect();
        app.getGroupHelper().clickEdit();
        app.getGroupHelper().fillGroupForm(new GroupData("edit_name", "edit_header", "editFooter"));
        app.getGroupHelper().clickUpdate();
        app.getnavigationHelper().returnGroupPage();
    }
}
