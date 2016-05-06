package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by alisa on 21.04.2016.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {

    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupModification();
    //сохраняем старый идентификатор из уже существ. последнего объекта в списке before (брать можно любой??)
    GroupData group = new GroupData(before.get(before.size() - 1).getId(), "test2", "test2", "test3");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().GroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    //приводим в соответствие оба списка
    before.remove(before.size() - 1);
    before.add(group);
    //сравниваем не сортированные списки, а несортированные множества
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}