package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    /*переменные before и after теперь содержат не количество, а список эл-тов*/
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("test21");
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    /* присваиваем max id: превращаем коллекцию в поток id(чисел) с пом. ф-ции mapToInt (преобразует
    объект в число, а метод getAsInt преобразует рез-т в целое число)  */
    group.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after);
  }

}
