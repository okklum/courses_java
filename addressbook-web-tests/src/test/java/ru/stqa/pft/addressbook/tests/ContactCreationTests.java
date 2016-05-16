package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactSuite;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    ContactSuite before = app.contact().all();
    File photo = new File("/src/test/resources/012.png");
    ContactData contact = new ContactData()
            .withFirstname("Vasya").withLastname("Pupkin").withMobilePhone("+79001234565")
            .withHomePhone("1234567").withGroup("test1").withPhoto(photo);
    app.contact().create(contact, true);
    assertThat(app.contact().counter(),equalTo(before.size() + 1));
    ContactSuite after = app.contact().all();
    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt(c -> c.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadContactCreation() {
    app.goTo().homePage();
    ContactSuite before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("'Vasya'").withLastname("Pupkin").withMobilePhone("+79001234569")
            .withGroup("test1");
    app.contact().create(contact, true);
    assertThat(app.contact().counter(),equalTo(before.size()));
    ContactSuite after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}
