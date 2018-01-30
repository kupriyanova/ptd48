package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
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

    public void selectGroup(int index) {
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

    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        clickSubmit();
        returnGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name ("selected[]"));
    }

    public boolean isThereAGroupName() {
        return isElementPresent(By.xpath ("//span[contains (text()='name')]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = element.getText();
            GroupData group = new GroupData(id, name, null, null);
            groups.add(group);
        }
        return groups;
    }
}
