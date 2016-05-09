package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by alisa on 21.04.2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    //if (!app.contact().isThereAContact()) {
    if (app.contact().list().size() == 0) {
      app.contact().create
              (new ContactData("Vasya", "Pupkin", "+79001234567", null, "test1"), true);
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    //сохраняем старый id в переменной index из уже существ. последнего объекта
    ContactData contact = new ContactData(before.get(index).getId(), "Vasya", "Pupkin",
            "+79001234566", "vasya.pupkin@web.de", null);
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    //приводим в соответствие оба списка, сортируем и сравниваем
    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}