package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class EditGroupTests extends TestBase {
    @Test
    public void testModificationGroup() {
        app.getNavigationHelper().gotoGroupPage();

        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("name", "header", "group footer"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        System.out.println("Before: " + before);
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().clickEdit();
        GroupData group = new GroupData("name3", "header", "group footer");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().clickUpdate();
        app.getGroupHelper().returnGroupPage();

        List<GroupData> after = app.getGroupHelper().getGroupList();
        System.out.println("After: " + after);
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
