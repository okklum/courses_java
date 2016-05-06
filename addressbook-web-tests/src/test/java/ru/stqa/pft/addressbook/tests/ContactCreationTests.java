package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
     /* оставляем поле e-mail заполненным по умолчанию
     * добавляем имя группы для выбора из списка групп при создании контакта */
    ContactData contact = new ContactData("Vasya", "Pupkin", "+79001234567", null, "test1");
    app.getContactHelper().createContact(contact, true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals (after.size(), before.size() + 1);

    //Ищем id с максимальным значением (будет в новой группе) в множестве after, чтобы сравнить множества
    int max = 0;
    for (ContactData c: after) {
      if (c.getId() > max) {
        max = c.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }

}
