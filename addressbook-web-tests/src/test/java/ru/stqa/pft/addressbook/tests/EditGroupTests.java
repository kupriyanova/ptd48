package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class EditGroupTests extends TestBase {
    @Test
    public void testModificationGroup() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("name", "header", "group footer"));
        }
        app.getGroupHelper().clickSelect();
        app.getGroupHelper().clickEdit();
        app.getGroupHelper().fillGroupForm(new GroupData("edit_name", "edit_header", "editFooter"));
        app.getGroupHelper().clickUpdate();
        app.getGroupHelper().returnGroupPage();
    }
}
