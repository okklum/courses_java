package ru.stqa.pft.mantis.model;


import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by alisa on 04.06.2016.
 */
@Entity
@Table(name = "mantis_user_table")

public class UserData {

  @Id
  @Column
  private int id;

  @Column
  private String username;

  @Column
  private String email;

  @Column
  private String password;

  @Column
  @Type(type = "short")
  private short access_level;

  //Getters
  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public int getAccess_level() {
    return access_level;
  }

  //Setters
  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public UserData withUsername(String username) {
    this.username = username;
    return this;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

  public UserData withPassword(String password) {
    this.password = password;
    return this;
  }

  public UserData withAccess_level(short access_level) {
    this.access_level = access_level;
    return this;
  }

}
