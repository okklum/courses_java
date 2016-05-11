package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactSuite;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
   }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" +id + "']")).click();
  }

  public void initContactDeletion() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void confirmContactDeletionAlert() {
    confirmAlert();
  }

  //немного отредактировала подсказку Алексея из общего чата
  public void modifyContactById (int id) {
    WebElement checkbox = wd.findElement(By.id("" + id));
    checkbox.findElement(By.xpath("//*[@id='maintable']//td[8]/a")).click();
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
    contactCache = null;
    /* Требуется возврат на страницу контактов = главную, чтобы тест нашел нужный локатор.*/
    app.goTo().homePage();
  }

  public void modify(ContactData contact) {
    modifyContactById(contact.getId());
    //при модификации контакта нет дропдауна с выбором группы
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    app.goTo().homePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    initContactDeletion();
    confirmContactDeletionAlert();
    contactCache = null;
    app.goTo().homePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public ContactSuite contactCache = null;

  public ContactSuite all() {
    if (contactCache != null) {
      return new ContactSuite(contactCache);
    }
    contactCache = new ContactSuite();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      //преобразовываем String id в int id
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      String lastname = element.findElement(By.xpath("//td[2]")).getText();
      String firstname = element.findElement(By.xpath("//td[3]")).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contactCache;
  }

}
