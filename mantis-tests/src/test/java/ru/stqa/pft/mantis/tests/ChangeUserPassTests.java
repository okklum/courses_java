package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.UserSuite;

import java.sql.*;
import java.util.HashSet;
import java.util.List;

/**
 * Created by alisa on 04.06.2016.
 */
public class ChangeUserPassTests extends TestBase{


  @Test
  public void testChangeUserPass() throws InterruptedException {
    UserSuite users = app.db().usersNoAdmin();
    UserData modifiedUser = users.iterator().next();
    app.registration().loginAsAdmin("administrator","root");
    app.registration().resetPass(modifiedUser);
    app.registration().logout();


  }






}
