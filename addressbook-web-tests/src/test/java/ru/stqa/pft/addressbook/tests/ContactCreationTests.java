package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
     /* оставляем поле e-mail заполненным по умолчанию
     * добавляем имя группы для выбора из списка групп при создании контакта */
    app.getContactHelper().createContact(new ContactData("Vasya", "Pupkin", "+79001234567", null, "test1"), true);
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals (after, before + 1);
  }

}
