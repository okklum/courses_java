package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by alisa on 21.04.2016.
 */
public class SessionHelper extends HelperBase {

  public SessionHelper(WebDriver wd, ApplicationManager app) {
    super (wd, app);
  }

  public void login(String username, String password) {
    type(By.name("user"),username);
    type(By.name("pass"),password);
    click(By.xpath("//form[@id='LoginForm']/input[3]"));
  }
}
