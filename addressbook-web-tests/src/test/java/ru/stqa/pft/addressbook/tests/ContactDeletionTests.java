package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by alisa on 21.04.2016.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    //if (!app.contact().isThereAContact()) {
    if (app.contact().list().size() == 0) {
      app.contact().create
              (new ContactData("Vasya", "Pupkin", "+79001234567", null, "test1"), true);
    }
  }

  @Test
  public void testContactDeletion(){
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals (after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }

}
