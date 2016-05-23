package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.Objects;

/**
 * Created by alisa on 21.04.2016.
 */
public class HelperBase {
  protected ApplicationManager app;
  protected WebDriver wd;

  /* Добавляем в конструктор ссылку на менеджера, чтобы хелперы видели друг друга через него */
  public HelperBase(WebDriver wd, ApplicationManager app) {
    this.wd = wd;
    this.app = app;
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = wd.findElement(locator).getAttribute("Value");
      if (!Objects.equals(text, existingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  protected void attach(By locator, File file) {
    if (file != null&&file.isFile()) {
        wd.findElement(locator).sendKeys(file.getAbsolutePath());
      }
    }


  protected void confirmAlert() {
    wd.switchTo().alert().accept();
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  // Метод для проверки наличия элемента
  protected boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}
