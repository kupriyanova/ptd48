package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name ("group_name"), groupData.getName());
        type(By.name ("group_header"), groupData.getHeader());
        type(By.name ("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name ("new"));
    }

    public void select(int index) {
        wd.findElements(By.name ("selected[]")).get(index).click();
    }

    public void clickDelete() {
        click(By.name ("delete"));
    }

    public void clickEdit() {
        click(By.name ("edit"));
    }

    public void returnGroupPage() {
        click(By.linkText ("group page"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        clickSubmit();
        groupCach = null;
        returnGroupPage();
    }

    public void edit(GroupData group) {
        selectGroupById(group.getId());
        clickEdit();
        fillGroupForm(group);
        clickUpdate();
        groupCach = null;
        returnGroupPage();
    }

    private void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        clickDelete();
        groupCach = null;
        returnGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name ("selected[]"));
    }

    public boolean isThereAGroupName() {
        return isElementPresent(By.xpath ("//span[contains (text()='name')]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Groups groupCach = null;

    public Groups all() {
        if (groupCach != null) {
            return new Groups(groupCach);
        }
        groupCach = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = element.getText();
            groupCach.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCach);
    }
}
