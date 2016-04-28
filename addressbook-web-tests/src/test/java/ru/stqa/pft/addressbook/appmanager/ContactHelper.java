package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by alisa on 21.04.2016.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super (wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.getFirstname ());
    type(By.name("lastname"),contactData.getLastname ());
    type(By.name("mobile"),contactData.getMobile ());
    type(By.name("email"),contactData.getEmail ());


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

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void initContactDeletion() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void confirmContactDeletionAlert() {
    confirmAlert();
  }

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void createContact(ContactData contact, boolean b) {
    initContactCreation();
    /* оставляем поле e-mail заполненным по умолчанию
     * добавляем имя группы для выбора из списка групп при создании контакта */
    fillContactForm(contact, b);
    submitContactCreation();
    /* Требуется возврат на страницу контактов = главную, чтобы тест нашел нужный локатор
     * Завтра переделаю в новый метод
      * Либо надо выяснить, как ссылаться на другой хелпер, чтобы не дублировать код*/
    click(By.linkText("home"));

  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
}
