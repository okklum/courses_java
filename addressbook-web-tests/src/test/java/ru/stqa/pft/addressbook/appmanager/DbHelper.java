package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactSuite;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.GroupSuite;

import java.util.List;

/**
 * Created by alisa on 23.05.2016.
 */
public class DbHelper {

  //private final SessionFactory sessionFactory;
  //потерялся файл конфига, нагуглила решение
  SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
  }

  public GroupSuite groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData").list();
    session.getTransaction().commit();
    session.close();
    return new GroupSuite(result);
  }

  public ContactSuite contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    //deprecated = '0000-00-00' - don`t deleted contacts
    // 'from contactdata' - object instead table
    List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.disconnect();
    return new ContactSuite(result);
  }

  public ContactData oneContact(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    /*ContactData thisContact = session.createQuery(String.
            format("from ContactData where deprecated = '0000-00-00' and id ='%s'", id))
            .list();*/
    Query query = session.createQuery("from ContactData where deprecated = '0000-00-00' and id = :id");
    query.setParameter("id", id);
    session.getTransaction().commit();
    session.disconnect();
    return (ContactData) query.uniqueResult();
  }
}
