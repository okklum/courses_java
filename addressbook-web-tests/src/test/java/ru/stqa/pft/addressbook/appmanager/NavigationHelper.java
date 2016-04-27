package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by alisa on 21.04.2016.
 */
public class NavigationHelper extends HelperBase{

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
      } else {
      click(By.linkText("groups"));
    }
  }

  public void returnToHomepage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    } else {
      click(By.linkText("home page"));
    }
  }

  public void gotoHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    } else {
      click(By.linkText("home"));
    }
  }
}
