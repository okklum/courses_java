package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

/**
 * Created by alisa on 20.04.2016.
 */
public class TestBase {

  //объявляем переменную статической, чтобы избавиться от создания ее копий для разных тестовых классов
  protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

  //Смена аннотации с Test на Suite, чтобы метод запускался один раз для всего тестового набора
  @BeforeSuite
  public void setUp() throws Exception {
    app.init(app);
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

}
