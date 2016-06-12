package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by alisa on 10.06.2016.
 */
public class RestAssuredTests extends TestBase {

  public RestAssuredTests() {
    super(properties);
  }

  //авторизация с пом. rest-assured
  @BeforeClass
  public void init() throws IOException {
    RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
  }

  @Test (enabled = false)
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test").withDescription("bla-bla");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

  @Test
  public void testShowLastIssues() throws IOException { //тут же проверка skipIfNotFixed
    Set<Issue> issues = getIssues();
    Issue issue = issues.iterator().next();
    String state = issue.getState();
    System.out.println("Всего тасков: " + issues.size());
    int maxId = issues.stream().mapToInt((g) ->g.getId()).max().getAsInt();
    skipIfNotFixed(maxId); //не выводим на печать check, если таск открыт
    String check = "Последний таск с id " + maxId + " и кодом статуса "+ state;
    System.out.println(check);
  }


  private int createIssue(Issue newIssue) throws IOException {
    String json = RestAssured.given()
            .parameter("subject", newIssue.getSubject())
            .parameter("description", newIssue.getDescription())
            .post("http://demo.bugify.com/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

}
