package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactSuite;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by alisa on 21.04.2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    //if (!app.contact().isThereAContact()) {
    if (app.contact().all().size() == 0) {
      app.contact().create
              (new ContactData()
                      .withFirstname("Vasya").withLastname("Pupkin").withMobile("+79001234567").withGroup("test1"),true);
    }
  }

  @Test
  public void testContactModification() {
    ContactSuite before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Vasya").withLastname("Pupkin")
            .withMobile("+79001234566").withEmail("vasya.pupkin@web.de");
    app.contact().modify(contact);
    assertThat(app.contact().counter(), equalTo(before.size()));
    ContactSuite after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact)
            .withAdded(modifiedContact)));
  }
}