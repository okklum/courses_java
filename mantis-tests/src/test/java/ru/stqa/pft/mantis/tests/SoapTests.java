package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

/**
 * Created by alisa on 06.06.2016.
 */
public class SoapTests extends TestBase{

  @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    //System.out.println(isIssueOpen(1)); //отладка: проверка статуса первого таска
    skipIfNotFixed(1); //test
    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test //создаем баг-репорт
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("Test issue1").withDescription("Test issue desc1")
            .withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }

  @Test
  public void testGetIssues() throws RemoteException, ServiceException, MalformedURLException {
    Set<Issue> issues = app.soap().getAllIssues(1);
    System.out.println("Всего тасков в системе: " + issues.size());
    for (Issue issue: issues) {
      System.out.println(issue.getResolution().getName());
    }
  }

}
