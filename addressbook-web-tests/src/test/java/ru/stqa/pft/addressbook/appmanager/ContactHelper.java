package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactSuite;

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
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("address"), contactData.getAddress());
    attach(By.name("photo"), contactData.getPhoto());


    if (creation) {
      //в форме создания контакта дропдаун выбора группы должен быть
      //new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      Assert.assertTrue(isElementPresent(By.name("new_group")));
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
    /* wd.findElement(By.cssSelector("input[id='" +id + "']")).click();
    * поменяла на чуть более удобочитаемый вид с помощью метода String.format*/
    wd.findElement(By.cssSelector(String.format("input[id='%s']", id))).click();
  }

  public void initContactDeletion() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void confirmContactDeletionAlert() {
    confirmAlert();
  }

  //немного отредактировала подсказку Алексея из общего чата
  public void initModifyContactById(int id) {
    WebElement checkbox = wd.findElement(By.id("" + id));
    checkbox.findElement(By.xpath("//*[@id='maintable']//td[8]/a")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void create(ContactData contact) {
    initContactCreation();
    /* оставляем поле e-mail заполненным по умолчанию
     * добавляем имя группы для выбора из списка групп при создании контакта */
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    /* Требуется возврат на страницу контактов = главную, чтобы тест нашел нужный локатор.*/
    app.goTo().homePage();
  }

  public void modify(ContactData contact) {
    initModifyContactById(contact.getId());
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

  public int counter() {
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
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      String firstname = element.findElement(By.xpath(".//td[3]")).getText();
      String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
      String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
      String address = element.findElement(By.xpath(".//td[4]")).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones).withAllEMailes(allEmails).withAddress(address));
    }
    return new ContactSuite(contactCache);
  }

  public ContactData infoEditForm(ContactData contact) {
    initModifyContactById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("Value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("Value");
    String home = wd.findElement(By.name("home")).getAttribute("Value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("Value");
    String work = wd.findElement(By.name("work")).getAttribute("Value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    //почему-то некоторые данные адресов возвращает только getText, а не getAttribute
    String address = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
  }


  public String infoCardForm(ContactData contact) {
    readContactCardById(contact.getId());
    String contactCardInfo = wd.findElement(By.xpath("//div[@id='content']")).getText();
    wd.navigate().back();
    return contactCardInfo;
  }

  private void readContactCardById(int id) {
    WebElement checkbox = wd.findElement(By.id("" + id));
    checkbox.findElement(By.xpath("//*[@id='maintable']//td[7]/a")).click();
  }
}
