package ru.stqa.pft.addressbook.tests.groups;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EditGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if(app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("name1").withHeader("header0").withFooter("group footer0"));
        }
    }
    @Test
    public void testModificationGroup() {
        Groups before = app.db().groups();

        GroupData editGroup = before.iterator().next();
        GroupData group = new GroupData().withId(editGroup.getId()).withName("name2")
                .withHeader("header").withFooter("group footer");

        app.goTo().groupPage();
        app.group().edit(group);
        assertThat(app.group().count(), equalTo(before.size()));

        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(editGroup).withAdded(group)));
    }
}
