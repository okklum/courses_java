package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

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

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactInfoTests::cleaned).collect(Collectors.joining("\n"));
  }

  //вспом. метод для чистки данных из формы редактирования контакта
  public static String cleaned(String phone) {
    //исключаем '+' из чистки, т.к.он не обрезается на странице контактов
    return phone.replaceAll("\\s","").replaceAll("\\-\\)\\(","");
  }

}
