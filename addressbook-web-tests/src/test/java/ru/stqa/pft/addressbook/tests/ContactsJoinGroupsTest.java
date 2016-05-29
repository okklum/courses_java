package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactSuite;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.GroupSuite;

import java.sql.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;


/**
 * Created by alisa on 25.05.2016.
 */
public class ContactsJoinGroupsTest extends TestBase {


  @BeforeClass
  public void checkData() {

    if (app.db().groups().size() ==0) {
      app.goTo().groupPage();
      //Создаем уникальную группу: currentTimeMillis почему-то записал только дату, нагуглила др. метод
      app.group().create(new GroupData().withName(String.format("test%s", new Date(System.nanoTime()))));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("Vasya").withLastname("Ivanov"));
    }
    //Создать метод для проверки связи выбранного контакта с группой
  }

  @Test
  public void testAddContactToGroup() {
    ContactSuite contacts = app.db().contacts();
    GroupSuite groups = app.db().groups();
    ContactData thisContact = contacts.iterator().next();
    GroupData thisGroup = groups.iterator().next();
    /* нафиг максимальный id, из списка по нему не выбрать
    GroupData thisGroup1 = groups.iterator().next().withId(groups.stream()
            .mapToInt(GroupData::getId).max().getAsInt());*/
    app.goTo().homePage();
    app.contact().selectContactById(thisContact.getId());
    app.contact().selectThisGroup(thisGroup);
    app.contact().addContactToGroup();
    app.goTo().homePage();
    ContactData dbContact = app.db().oneContact(thisContact.getId());

    assertThat(dbContact.getGroups(), contains(thisGroup));
  }

  @Test(enabled = false)
  public void testDeleteContactFromGroup() {
  }
}
