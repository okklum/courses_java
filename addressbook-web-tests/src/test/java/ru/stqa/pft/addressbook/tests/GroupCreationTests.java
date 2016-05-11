package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.GroupSuite;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    /*переменные before и after теперь содержат не количество, а список эл-тов*/
    GroupSuite before = app.group().all();
    GroupData group = new GroupData().withName("test21");
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
