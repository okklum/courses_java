package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import ru.stqa.pft.rest.ru.stqa.pft.rest.model.Issue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Created by alisa on 10.06.2016.
 */
public class TestBase {

  public static Properties properties;

  public TestBase(Properties properties) {
    this.properties = properties;
  }

  @BeforeClass
  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public Set<Issue> getIssues() throws IOException {
    //закомментирован неработаюший метод getProperty
    // String json = RestAssured.get(getProperty("rest.baseUrl")).asString();
    String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType()); //== new Set<Issue>
  }

  boolean isIssueOpen() throws IOException {
    Set<Issue> allIssues = getIssues();
    Issue issues = allIssues.iterator().next();
    if (issues.getState().contains("1")) { //код для тасков в статусе Open
      return true;
    }
    return false;
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen()) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }



}
