package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by alisa on 21.04.2016.
 */
public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletion(){

    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact
              (new ContactData("Vasya", "Pupkin", "+79001234567", null, "test1"), true);
    }
    app.getContactHelper().selectContact(before - 1);
    app.getContactHelper().initContactDeletion();
    app.getContactHelper().confirmContactDeletionAlert();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals (after, before - 1);
  }

}
