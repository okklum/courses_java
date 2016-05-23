package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactSuite;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by alisa on 21.04.2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create
              (new ContactData()
                      .withFirstname("Vasya").withLastname("Pupkin").withMobilePhone("+79001234567")
                      .withEmail("vasya@mail.ru"));
    }
    /*app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create
              (new ContactData()
                      .withFirstname("Vasya").withLastname("Pupkin").withMobilePhone("+79001234567")
                      .withGroup("test1"));
    }*/
  }

  @Test
  public void testContactModification() {
    ContactSuite before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Vasya").withLastname("Pupkin")
            .withMobilePhone("+79001234562").withEmail("vasya.pupkin@web.de");
    app.contact().modify(contact);
    assertThat(app.contact().counter(), equalTo(before.size()));
    ContactSuite after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact)
            .withAdded(modifiedContact)));
  }
}