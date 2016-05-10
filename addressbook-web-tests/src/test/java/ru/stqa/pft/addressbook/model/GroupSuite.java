package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by alisa on 10.05.2016.
 */
public class GroupSuite extends ForwardingSet<GroupData> {

  private Set<GroupData> delegateOb;

  /*
  копируем множество групп для новых методов: берем множество из существующего объекта delegateOb,к-рый передан в
  кач-ве параметра, строим новое множество с теми же эл-тами и присваиваем это множ-во в кач-ве атрибута в новый
  создаваемый этим конструктором объект (с).

  Хз, зачем ссылка на возвращаемое из метода значение, почему не groupSuite из параметра конструктора?
  */
  public GroupSuite(GroupSuite groupSuite) {
    this.delegateOb = new HashSet<GroupData>(groupSuite.delegate());
  }

  public GroupSuite() {
    this.delegateOb = new HashSet<>();
  }

  @Override
  //обязательный для декоратора метод delegateOb
  protected Set<GroupData> delegate() {
    return delegateOb;
  }

  /** Методы withAdded и without создают соотв. копию множества
    */
  public GroupSuite withAdded(GroupData group) {
    GroupSuite groupSuite = new GroupSuite(this);
    groupSuite.add(group);
    return groupSuite;
  }

  public GroupSuite without(GroupData group) {
    GroupSuite groupSuite = new GroupSuite(this);
    groupSuite.remove(group);
    return groupSuite;
  }
}
