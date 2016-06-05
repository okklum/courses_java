package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.pft.mantis.model.UserData;

/**
 * Created by alisa on 29.05.2016.
 */
public class RegistrationHelper extends HelperBase {

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("input[value='Signup']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void logout() {
    click(By.linkText("Logout"));
  }

  public void resetPass(UserData user) throws InterruptedException {
    click(By.linkText("Manage")); //openserver интересно открывает...
    new WebDriverWait(wd,5).until(ExpectedConditions.elementToBeClickable(By.linkText("Manage Users")));
    click(By.linkText("Manage Users"));
    click(By.linkText(user.getUsername()));
    click(By.xpath("//input[@value='Reset Password']"));
  }

  public void setNewPass(String changePassLink, String password) {
    wd.get(changePassLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }
}
