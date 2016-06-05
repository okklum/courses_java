package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by alisa on 05.06.2016.
 */
public class UserSuite extends ForwardingSet<UserData> {

  private Set<UserData> delegateOb;

  /*public UserSuite(UserSuite users) {
    this.delegateOb = new HashSet<>();
  }*/

  public UserSuite(Collection<UserData> users) {
    this.delegateOb = new HashSet<>(users);
  }

  @Override
  //обязательный для декоратора метод delegateOb
  protected Set delegate() {
    return delegateOb;
  }

}
