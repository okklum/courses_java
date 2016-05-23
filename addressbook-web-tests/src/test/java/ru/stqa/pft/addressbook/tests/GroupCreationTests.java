package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.GroupSuite;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> readyGroupsFromXML() throws IOException {
    //FileReader in BufferedReader for use his metod (.readLine);   try ~ writer.close();
    try (BufferedReader reader = new BufferedReader(new FileReader
            (new File("src/test/resources/groups.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        //читаем все данные по строчкам
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(GroupData.class);
      //Приведение типа, метод возвращает не хз что, а список объектов типа групдата
      List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
      //магия: превращаем список объектов в массивы с 1 объектом, преобразуем в список, забираем
      return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> readyGroupsFromJSON() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader
            (new File("src/test/resources/groups.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        //читаем все данные по строчкам
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType()); //List<GroupData>.class
      //магия: превращаем список объектов в массивы с 1 объектом, преобразуем в список, забираем
      return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "readyGroupsFromJSON")
  public void testGroupCreation(GroupData group) {
    GroupSuite before = app.db().groups();
    app.goTo().groupPage();
    app.group().create(group);
    assertThat(app.group().counter(), equalTo(before.size() + 1));
    GroupSuite after = app.db().groups();
    /* присваиваем max id: превращаем коллекцию в поток id(чисел) с пом. ф-ции mapToInt (преобразует
    объект в число, а метод getAsInt преобразует рез-т в целое число)  */
    assertThat(after, equalTo
            (before.withAdded(group.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadGroupCreation() {
    GroupSuite before = app.db().groups();
    app.goTo().groupPage();
    GroupData group = new GroupData().withName("test21'");
    app.group().create(group);
    //добавляем быстрое сравнение размера перед медленной загрузкой и сравнением всех объектов
    assertThat(app.group().counter(), equalTo(before.size()));
    GroupSuite after = app.db().groups();
    assertThat(after, equalTo(before));
  }

}
