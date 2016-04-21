package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by alisa on 21.04.2016.
 */
public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletion(){

    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactDeletion();
    app.getContactHelper().confirmContactDeletion();
    app.getNavigationHelper().gotoHomePage();
  }

}
