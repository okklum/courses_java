package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Vasya", "Pupkin", "+79001234567", "vasya.pupkin@web.de"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomepage();
  }

}
