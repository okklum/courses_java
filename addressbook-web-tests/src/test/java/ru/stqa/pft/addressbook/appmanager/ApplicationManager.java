package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by alisa on 20.04.2016.
 */
public class ApplicationManager {

  WebDriver wd;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private ContactHelper contactHelper;
  private GroupHelper groupHelper;
  private String browser;
  private ApplicationManager app;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
    if (Objects.equals(browser, BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
      //Еще один вариант записи сравнения объектов вручную; первый быстрее и нагляднее
    } else if (browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (Objects.equals(browser, BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    }

    //уменьшила таймаут с 60 SECONDS до 0,5
    wd.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    wd.get("http://addressbook/");
    //переделана инициализация хелперов (+app) для связи хелперов друг с другом через app менеджер
    sessionHelper = new SessionHelper(wd, app);
    navigationHelper = new NavigationHelper(wd, app);
    contactHelper = new ContactHelper(wd, app);
    groupHelper = new GroupHelper(wd, app);
    sessionHelper.login("admin", "secret");
  }


  public void stop() {
    wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
