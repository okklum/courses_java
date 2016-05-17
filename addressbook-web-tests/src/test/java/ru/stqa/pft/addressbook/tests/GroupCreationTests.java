package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.GroupSuite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups(){
    List<Object[]> list = new ArrayList<Object[]>();
    /* Заменяем массив из строк на массив из объектов, точнее, объекта, т.к. параметр один
    list.add(new Object[] {"test1", "header1", "footer1"}); */
    list.add(new Object[] {new GroupData().withName("test1").withHeader("header2").withFooter("footer1")});
    list.add(new Object[] {new GroupData().withName("test2").withHeader("header2").withFooter("footer2")});
    list.add(new Object[] {new GroupData().withName("test3").withHeader("header3").withFooter("footer3")});
    return list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
    app.goTo().groupPage();
    /*переменные before и after теперь содержат не количество, а список эл-тов*/
    GroupSuite before = app.group().all();
    app.group().create(group);
    assertThat(app.group().counter(), equalTo(before.size() + 1));
    GroupSuite after = app.group().all();
    /* присваиваем max id: превращаем коллекцию в поток id(чисел) с пом. ф-ции mapToInt (преобразует
    объект в число, а метод getAsInt преобразует рез-т в целое число)  */
    assertThat(after, equalTo
            (before.withAdded(group.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadGroupCreation() {
    app.goTo().groupPage();
    GroupSuite before = app.group().all();
    GroupData group = new GroupData().withName("test21'");
    app.group().create(group);
    //добавляем быстрое сравнение размера перед медленной загрузкой и сравнением всех объектов
    assertThat(app.group().counter(), equalTo(before.size()));
    GroupSuite after = app.group().all();
    assertThat(after, equalTo(before));
  }

}
