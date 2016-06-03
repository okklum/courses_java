package ru.stqa.pft.mantis.model;

/**
 * Created by alisa on 02.06.2016.
 */
public class MailMessage {

  public String to;
  public String text;

  public MailMessage(String to, String text) {
    this.to = to;
    this.text = text;
  }

}
