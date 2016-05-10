package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by alisa on 11.05.2016.
 */
public class ContactSuite extends ForwardingSet<ContactData> {

  private Set<ContactData> delegateOb;

  public ContactSuite(ContactSuite contactSuite) {
    this.delegateOb = new HashSet<>(contactSuite.delegate());
  }

  public ContactSuite() {
    this.delegateOb = new HashSet<>();
  }

  @Override
  //обязательный для декоратора метод delegateOb
  protected Set delegate() {
    return delegateOb;
  }

  public ContactSuite withAdded(ContactData contact) {
    ContactSuite contactSuite = new ContactSuite(this);
    contactSuite.add(contact);
    return contactSuite;
  }

  public ContactSuite without(ContactData contact) {
    ContactSuite contactSuite = new ContactSuite(this);
    contactSuite.remove(contact);
    return contactSuite;
  }
}
