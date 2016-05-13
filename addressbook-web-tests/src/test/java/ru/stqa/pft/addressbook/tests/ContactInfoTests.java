package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by alisa on 12.05.2016.
 */
public class ContactInfoTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create
              (new ContactData()
                      .withFirstname("Vasya").withLastname("Pupkin").withHomePhone("1234567")
                      .withMobilePhone("+79001234567").withWorkPhone("43532134567").withGroup("test1"),true);
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoEditForm = app.contact().infoEditForm(contact);

    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoEditForm.getHomePhone())));
    assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoEditForm.getMobilePhone())));
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoEditForm.getWorkPhone())));
  }

  public String cleaned(String phone) {
    //как добавить экранированный символ "+" в регулярное выражение?
    return phone.replaceAll("\\s","").replaceAll("[^0-9]","");
  }

}
