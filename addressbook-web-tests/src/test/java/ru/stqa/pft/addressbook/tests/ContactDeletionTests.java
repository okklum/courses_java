package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by alisa on 21.04.2016.
 */
public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletion(){

    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact
              (new ContactData("Vasya", "Pupkin", "+79001234567", null, "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactDeletion();
    app.getContactHelper().confirmContactDeletionAlert();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals (after.size(), before.size() - 1);
  }

}
