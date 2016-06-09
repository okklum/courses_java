package ru.stqa.pft.mantis.model;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;

/**
 * Created by alisa on 07.06.2016.
 */
public class Issue {

  private int id;
  private String summary;
  private String description;
  private Project project;
  private ObjectRef status;

  public ObjectRef getResolution() {
    return resolution;
  }

  public Issue withResolution(ObjectRef resolution) {
    this.resolution = resolution;
    return this;
  }

  private ObjectRef resolution;


  public int getId() {
    return id;
  }

  public String getSummary() {
    return summary;
  }

  public String getDescription() {
    return description;
  }

  public Project getProject() {
    return project;
  }

  public ObjectRef getStatus() {
    return status;
  }

  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  public Issue withSummary(String summary) {
    this.summary = summary;
    return this;
  }

  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }

  public Issue withProject(Project project) {
    this.project = project;
    return this;
  }

  public Issue withStatus(ObjectRef status) {
    this.status = status;
    return this;
  }


  @Override
  public String toString() {
    return "Issue{" +
            "id=" + id +
            ", summary='" + summary + '\'' +
            ", description='" + description + '\'' +
            ", project=" + project +
            ", status=" + status +
            '}';
  }
}
