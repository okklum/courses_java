package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alisa on 21.04.2016.
 */
public class ContactHelper extends HelperBase {

  //передаем в хелпер ссылку на менеджера, чтобы хелперы видели друг друга
  public ContactHelper(WebDriver wd, ApplicationManager app) {
    super(wd, app);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());


    if (creation) {
      //в форме создания контакта дропдаун выбора группы должен быть
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      //в форме модификации элемента быть не должно
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    //click(By.name("selected[]"));
  }

  public void initContactDeletion() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void confirmContactDeletionAlert() {
    confirmAlert();
  }

  public void initContactModification(int index) {
    /*Непонятно, почему не сработал записанный рекордером локатор
    И как правильно писать локатор с указанием класса? есть уникальный odd и center, вроде перечислять нужно все...
    wd.findElements(By.xpath("//table[@id='maintable']//[@class='odd']//[@class='center']//td[8]/a/img")).get(index).click();
    */
    wd.findElements(By.xpath("//*[@id='maintable']//td[8]/a/img")).get(index).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void create(ContactData contact, boolean b) {
    initContactCreation();
    /* оставляем поле e-mail заполненным по умолчанию
     * добавляем имя группы для выбора из списка групп при создании контакта */
    fillContactForm(contact, b);
    submitContactCreation();
    /* Требуется возврат на страницу контактов = главную, чтобы тест нашел нужный локатор.
    Логичнее вставить его тут, чем добавлять в каждый тест лишнюю строчку возврата на главную.
     Для этого юзаем метод другого хелпера, убираем click(By.linkText("home"));*/
    app.goTo().homePage();
  }

  public void modify(int index, ContactData contact) {
    initContactModification(index);
    //при модификации контакта нет дропдауна с выбором группы
    fillContactForm(contact, false);
    submitContactModification();
    app.goTo().homePage();
  }

  public void delete(int index) {
    app.contact().selectContact(index);
    app.contact().initContactDeletion();
    app.contact().confirmContactDeletionAlert();
    app.goTo().homePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      //преобразовываем String id в int id
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      String lastname = element.findElement(By.xpath("//td[2]")).getText();
      String firstname = element.findElement(By.xpath("//td[3]")).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }
}
