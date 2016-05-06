package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
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

  public void selectGroup(int index) {
    //выбираем уже определенный элемент в списке по параметру index
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void GroupModification() {
    click(By.name("update"));
  }

  public void createGroup(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    //return wd.findElements(By.name("selected[]")).size();
    return wd.findElements(By.className("group")).size();

  }

  public List<GroupData> getGroupList() {
    //Создаем список groups, который будем заполнять, с указанием конкретного класса ArrayList
    List<GroupData> groups = new ArrayList<GroupData>();
    /* Извлекаем данные со страницы веб-приложения: пока только название группы,
     * в селекторе все элементы класса group в теге span*/
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    //Получаем список объектов типа WebElement, из каждого извлекаем текст = имя группы
    for (WebElement element: elements) {
      String name = element.getText();
      //Получаем уникальный атрибут Value из <input type="checkbox" title="Select (test1)" value="45" name="selected[]"/>
      String id = element.findElement(By.tagName("input")).getAttribute("Value");
      //создаем новый объект group типа GroupData, заполняем известными данными
      GroupData group = new GroupData(id, name, null, null);
      //добавляем созданный объект group в список groups
      groups.add(group);
    }
    return groups;
  }
}
