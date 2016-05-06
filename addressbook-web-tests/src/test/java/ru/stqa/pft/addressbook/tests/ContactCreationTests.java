package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
     /* оставляем поле e-mail заполненным по умолчанию
     * добавляем имя группы для выбора из списка групп при создании контакта */
    ContactData contact = new ContactData("Vasya", "Pupkin", "+79001234566", null, "test1");
    app.getContactHelper().createContact(contact, true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals (after.size(), before.size() + 1);

    //Ищем id с максимальным значением (будет в новой группе) в потоке after, чтобы сравнить множества
    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
