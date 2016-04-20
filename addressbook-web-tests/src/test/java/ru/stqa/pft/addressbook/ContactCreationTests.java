package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    initContactCreation();
    fillContactForm(new ContactData("Vasya", "Pupkin", "+79001234567", "vasya.pupkin@web.de"));
    submitContactCreation();
    returnToHomepage();
  }

}
