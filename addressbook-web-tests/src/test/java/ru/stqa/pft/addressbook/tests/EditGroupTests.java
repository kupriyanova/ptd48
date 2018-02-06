package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EditGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();

        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("name2").withHeader("header").withFooter("group footer"));
        }
    }
    @Test
    public void testModificationGroup() {
        Groups before = app.group().all(); //получение списка до редактирования
        GroupData editGroup = before.iterator().next();
        GroupData group = new GroupData().withId(editGroup.getId()).withName("name2").withHeader("header").withFooter("group footer"); //данные которые будут вноситься при редактировании

        app.group().edit(group); //редактирование группы

        Groups after = app.group().all(); //получение списка после редактирования
        assertThat(after.size(), equalTo(before.size())); //сравнение размеров множеств

        assertThat(after, equalTo(before.without(editGroup).withAdded(group)));
    }
}
