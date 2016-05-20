package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by alisa on 20.04.2016.
 */
public class ApplicationManager {

  private final Properties properties;
  WebDriver wd;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private ContactHelper contactHelper;
  private GroupHelper groupHelper;
  private String browser;


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  //В метод добавлен параметр ApplicationManager app для связи хелперов друг с другом через app менеджер
  public void init(ApplicationManager app) throws IOException {
    //properties from file "local.properties"
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

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
    //wd.get("http://addressbook/");
    wd.get(properties.getProperty("web.baseUrl"));
    //переделана инициализация хелперов (+app) для связи хелперов друг с другом через app менеджер
    sessionHelper = new SessionHelper(wd, app);
    navigationHelper = new NavigationHelper(wd, app);
    contactHelper = new ContactHelper(wd, app);
    groupHelper = new GroupHelper(wd, app);
    //sessionHelper.login("admin", "secret");
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
  }


  public void stop() {
    wd.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }
}
