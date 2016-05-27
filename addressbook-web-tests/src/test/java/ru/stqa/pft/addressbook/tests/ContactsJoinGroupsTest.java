package ru.stqa.pft.addressbook.tests;

/*Группы сортируются по алфавиту и добавляется первая в списке. Можно создать группу с именем типа
* '00000000-timestamp', также она будет обладать максимальным id (отбор по значению с пом. max)
* или нужно как-то доставать id контакта и группы и проверять, что для данного контакта возвращается эта группа
* */

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactSuite;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.GroupSuite;

import java.sql.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by alisa on 25.05.2016.
 */
public class ContactsJoinGroupsTest extends TestBase {


  @BeforeClass
  public void checkGroups() {

    if (app.db().groups().size() > 0) { //проверка не работает
      app.db().groups().clear();
    }
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

  @Test(enabled = false)
  public void checkDate(){
    System.out.println(new Date(System.nanoTime()));
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

    /*нужна замена сравения на вхождение
    assertThat(dbContact.getGroups(), equalTo(thisContact.getGroups()));*/

  }

  @Test(enabled = false)
  public void testDeleteContactFromGroup() {
  }
}
