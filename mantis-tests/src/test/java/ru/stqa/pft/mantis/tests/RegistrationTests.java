package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by alisa on 30.05.2016.
 */
public class RegistrationTests extends TestBase{

  @Test
  public void testRegistration() {
    app.registration().start("user1","user1@localhost.localdomain");


  }
}