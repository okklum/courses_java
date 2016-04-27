package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().initContactCreation();
    /* оставляем поле e-mail заполненным по умолчанию
     * добавляем имя группы для выбора из списка групп при создании контакта */
    app.getContactHelper().fillContactForm(new ContactData("Vasya", "Pupkin", "+79001234567", null, "test1"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomepage();
  }

}
