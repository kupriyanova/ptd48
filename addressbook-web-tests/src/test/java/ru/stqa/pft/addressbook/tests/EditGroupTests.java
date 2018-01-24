package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class EditGroupTests extends TestBase {
    @Test
    public void testModificationGroup() {
        app.getNavigationHelper().gotoGroupPage();

        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("name", "header", "group footer"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(0);
        app.getGroupHelper().clickEdit();
        app.getGroupHelper().fillGroupForm(new GroupData("edit_name", "edit_header", "editFooter"));
        app.getGroupHelper().clickUpdate();
        app.getGroupHelper().returnGroupPage();

        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
    }
}
