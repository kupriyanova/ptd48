package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

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

    public void clickSubmit() {
        click(By.name ("submit"));
    }

    public void clickSelect() {
        click(By.name ("selected[]"));
    }

    public void clickDelete() {
        click(By.name ("delete"));
    }
}