package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

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
                      .withMobilePhone("+7(900)1234567").withWorkPhone("435 321 34 56").withEmail("vasya@mail.ru")
                      .withEmail("vasya-work@mail.ru").withAddress("Советский союз").withGroup("test1"),true);
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoEditForm = app.contact().infoEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoEditForm)));
    assertThat(contact.getAllEMailes(), equalTo(mergeEmailes(contactInfoEditForm)));
    assertThat(contact.getAddress(), equalTo(cleanedTabs(contactInfoEditForm.getAddress())));
  }

  private String mergeEmailes(ContactData contact) {
    return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3()).stream()
            .filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactInfoTests::cleaned).collect(Collectors.joining("\n"));
  }

  //вспом. метод для чистки данных из формы редактирования контакта
  public static String cleaned(String phone) {
    //не включаем '+', т.к.он не обрезается на странице контактов; исправила выражение
    return phone.replaceAll("\\s","").replaceAll("[-)(\\.]","");
  }

  public static String cleanedTabs(String data) {
    //на всякий случай чистим переносы строк и пробелы на конце в данных (могут быть в адресе)
    return data.replaceAll("\\s$","");
  }

}
