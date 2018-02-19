package ru.stqa.pft.addressbook.tests.groups;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class CreationGroupTests extends TestBase {

    @Test
    public void testCreationGroup() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("name2").withHeader("header").withFooter("group footer");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));

        Groups after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBugCreationGroup() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("name2'").withHeader("header").withFooter("group footer");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));

        Groups after = app.group().all();
        assertThat(after, equalTo(before));
    }
}