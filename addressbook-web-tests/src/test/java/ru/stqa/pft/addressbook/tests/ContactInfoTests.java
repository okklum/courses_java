package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                      .withFirstname("Vasya").withLastname("Pupkin").withHomePhone("123-45-67")
                      .withMobilePhone("+7(900)1234567").withWorkPhone("435 321 34 56").withEmail("vasya@mail.ru")
                      .withEmail2("vasya-work@mail.ru").withEmail3("vasya3@ya.ru").withAddress("Советский союз")
                      .withGroup("test1"),true);
    }
  }

  @Test
  public void testContactData() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoEditForm = app.contact().infoEditForm(contact);
    String infoCardForm = app.contact().infoCardForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoEditForm)));
    assertThat(contact.getAllEMailes(), equalTo(mergeEmailes(contactInfoEditForm)));
    assertThat(contact.getAddress(), equalTo(cleanedTabs(contactInfoEditForm.getAddress())));

   //Сравниваем данные из формы редактирования контакта с карточкой контакта
    assertThat(mergeData(contactInfoEditForm), equalTo(infoCardForm));

  }

  //Сбор всех данных со страницы редактирования контакта
  private String mergeData(ContactData contact) {
    String names = Arrays.asList(contact.getFirstname() + " ",contact.getLastname()).stream()
            .filter((s) -> !s.equals("")).collect(Collectors.joining(""));

    String address = Arrays.asList(contact.getAddress()).stream()
            .filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));

    String homePhone = "H: " + contact.getHomePhone();
    String mobilePhone = "M: " + contact.getMobilePhone();
    String workPhone = "W: " + contact.getWorkPhone();
    String allPhones = Arrays.asList(homePhone, mobilePhone, workPhone).stream().
            filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));

    //Добавляем к email замену логина на имя домена
    String email = contact.getEmail() + " (" + contact.getEmail()
            .replaceAll("^([a-z0-9_\\.-]+)@", "www.") + ")";
    String email2 = contact.getEmail2() + " (" + contact.getEmail2()
            .replaceAll("^([a-z0-9_\\.-]+)@", "www.") + ")";
    String email3 = contact.getEmail3() + " (" + contact.getEmail3()
            .replaceAll("^([a-z0-9_\\.-]+)@", "www.") + ")";

    String allEmails = Arrays.asList(email, email2, email3).stream().
            filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));

    String group = Arrays.asList("Member of:" + contact.getGroup()).stream().filter((s) -> !s.equals(""))
            .map(ContactInfoTests::cleanedNull).collect(Collectors.joining("\n"));

    String allData = names + "\n" + address + "\n" + "\n" + allPhones + "\n" + "\n" + allEmails
            + "\n"+ "\n" + "\n" + group + "\n";
    return allData;
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

  public static String cleanedNull(String data) {
    return data.replaceAll("null","");
  }

  public static String cleanedTabs(String data) {
    //на всякий случай чистим переносы строк и пробелы на конце в данных (могут быть в адресе)
    return data.replaceAll("\\s$","");
  }
}
