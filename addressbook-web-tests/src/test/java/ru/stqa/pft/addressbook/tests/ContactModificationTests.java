package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by alisa on 21.04.2016.
 */
public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {

    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Vasya", "Pupkin", "+79001234567", "vasya.pupkin@web.de"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();

  }
}
