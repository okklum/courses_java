package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.UserSuite;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by alisa on 04.06.2016.
 */
public class ChangeUserPassTests extends TestBase{

  @BeforeClass
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangeUserPass() throws InterruptedException, IOException {
    UserSuite users = app.db().usersNoAdmin();
    UserData modifiedUser = users.iterator().next();
    String newpassword = "newpassword1";
    app.registration().login("administrator","root");
    app.registration().resetPass(modifiedUser);
    app.registration().logout();
    //получаем письма
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String changePassLink = findChangePassLink(mailMessages, modifiedUser);
    //app.registration().setNewPass(changePassLink, newpassword);
    app.registration().finish(changePassLink,newpassword);
    assertTrue(app.newSession().httpLogin(modifiedUser.getUsername(),newpassword));


  }

  private String findChangePassLink(List<MailMessage> mailMessages, UserData modifiedUser) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals
            (modifiedUser.getEmail())).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterClass(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
