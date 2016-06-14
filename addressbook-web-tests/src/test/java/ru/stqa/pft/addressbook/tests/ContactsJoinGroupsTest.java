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
import static org.hamcrest.Matchers.not;


/**
 * Created by alisa on 25.05.2016.
 */
public class ContactsJoinGroupsTest extends TestBase {


  @BeforeClass
  public void checkData() {

    if (app.db().groups().size() ==0) {
      app.goTo().groupPage();
      //Создаем уникальную группу
      app.group().create(new GroupData().withName(String.format("test%s", new Date(System.nanoTime()))));
    }
    //Вместо сложных проверок просто добавляем контакт; далее найдем его по максимальному id
    app.goTo().homePage();
    app.contact().create(new ContactData().withFirstname("Test").withLastname("oops"));
  }


  @Test
  public void testContactsJoinGroups() {
    
    ContactSuite contacts = app.db().contacts();
    GroupSuite groups = app.db().groups();
     //Юзаем максимальный id: группу по нему не выбрать, зато контакт можно!
    GroupData thisGroup = groups.iterator().next();
    ContactData thisContact = contacts.iterator().next().withId(contacts.stream()
            .mapToInt(ContactData::getId).max().getAsInt());
    app.goTo().homePage();
    app.contact().selectContactById(thisContact.getId());
    app.contact().selectThisGroup(thisGroup);
    app.contact().addContactToGroup();
    app.goTo().homePage();
    ContactData dbContact = app.db().oneContact(thisContact.getId());
    assertThat(dbContact.getGroups(), contains(thisGroup));
    System.out.println("Контакт с id " + thisContact.getId() + " добавлен в группу " + thisGroup.getName());

    app.goTo().homePage();
    app.contact().showContactsInGroup(thisGroup);
    app.contact().selectContactById(thisContact.getId());
    app.contact().deleteContactFromGroup();
    app.goTo().homePage();
    ContactData dbContactWithoutGroup = app.db().oneContact(thisContact.getId());
    assertThat(dbContactWithoutGroup.getGroups(), not(hasItem(thisGroup)) );
    System.out.println("Контакт с id " + thisContact.getId() + " удален из группы " + thisGroup.getName());
  }
}
