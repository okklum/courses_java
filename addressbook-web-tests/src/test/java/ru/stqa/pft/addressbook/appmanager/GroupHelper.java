package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.GroupSuite;

import java.util.List;

/**
 * Created by alisa on 21.04.2016.
 */
public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd, ApplicationManager app) {
    super(wd, app);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='" +id + "']")).click();
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void GroupModification() {
    click(By.name("update"));
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCache = null;
    returnToGroupPage();
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    GroupModification();
    groupCache = null;
    returnToGroupPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteSelectedGroups();
    groupCache = null;
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int counter() {
    //return wd.findElements(By.name("selected[]")).size();
    return wd.findElements(By.className("group")).size();
  }

  public GroupSuite groupCache = null;

  public GroupSuite all() {
    if (groupCache != null) {
      //возвращаем копию кеша, чтобы не испортить его
      return new GroupSuite(groupCache);
    }
    groupCache = new GroupSuite();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    //Получаем список объектов типа WebElement, из каждого извлекаем текст = имя группы
    for (WebElement element : elements) {
      String name = element.getText();
      //Получаем уникальный атрибут Value, для вычисления max id преобразуем новый int id из строки в число
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("Value"));
      groupCache.add(new GroupData().withId(id).withName(name));
    }
    return new GroupSuite(groupCache);
  }

}
