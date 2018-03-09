package ru.stqa.pft.addressbook.tests.groups;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeletionGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        if(app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("name1").withHeader("header0").withFooter("group footer0"));
        }
    }

    @Test
    public void testDeletionGroup() {
        Groups before = app.db().groups();
        app.goTo().groupPage();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);

        assertThat(app.group().count(), equalTo(before.size()-1));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(deletedGroup)));
    }
}
