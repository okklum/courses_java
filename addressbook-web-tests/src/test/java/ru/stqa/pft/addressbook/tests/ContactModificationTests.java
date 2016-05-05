package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by alisa on 21.04.2016.
 */
public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {

    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact
              (new ContactData("Vasya", "Pupkin", "+79001234567", null, "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size() - 1);
    //при модификации контакта нет дропдауна с выбором группы
    app.getContactHelper().fillContactForm(new ContactData("Vasya", "Pupkin",
            "+79001234566", "vasya.pupkin@web.de", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals (after.size(), before.size());
 }
}