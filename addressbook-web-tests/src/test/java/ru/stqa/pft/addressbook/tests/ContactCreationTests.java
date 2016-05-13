package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactSuite;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    ContactSuite before = app.contact().all();
     /* оставляем поле e-mail заполненным по умолчанию
     * добавляем имя группы для выбора из списка групп при создании контакта */
    ContactData contact = new ContactData()
            .withFirstname("Vasya").withLastname("Pupkin").withMobilePhone("+79001234564")
            .withHomePhone("1234567").withAddress("").withGroup("test1");
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
     /* оставляем поле e-mail заполненным по умолчанию
     * добавляем имя группы для выбора из списка групп при создании контакта */
    ContactData contact = new ContactData()
            .withFirstname("'Vasya'").withLastname("Pupkin").withMobilePhone("+79001234569").withGroup("test1");
    app.contact().create(contact, true);
    assertThat(app.contact().counter(),equalTo(before.size()));
    ContactSuite after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}
