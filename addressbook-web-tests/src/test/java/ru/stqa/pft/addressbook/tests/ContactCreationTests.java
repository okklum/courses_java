package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactSuite;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> readyContacts() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xStream = new XStream();
    xStream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
    return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
  }


  @Test(dataProvider = "readyContacts")
  public void testContactCreation(ContactData contact) {
    app.goTo().homePage();
    ContactSuite before = app.contact().all();
    /* File photo = new File("/src/test/resources/012.png");
    ContactData contact = new ContactData()
            .withFirstname("Vasya").withLastname("Pupkin").withMobilePhone("+79001234565")
            .withHomePhone("1234567").withGroup("test1").withPhoto(photo);*/
    app.contact().create(contact);
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
    app.contact().create(contact);
    assertThat(app.contact().counter(),equalTo(before.size()));
    ContactSuite after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}
