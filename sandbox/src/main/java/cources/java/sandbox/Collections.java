package cources.java.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alisa on 03.05.2016.
 */
public class Collections {

  public static void main(String [] args) {
    String [] langs = {"Java", "C+", "Python", "PHP"};

    /* length for array
    for (int i = 0; i < langs.length; i++) {
      System.out.println("Я хочу выучить " + langs[i]);
    }*/

    /* Array to List, massiv v spisok
    List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("PHP");*/
    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");


    //iterates for collection/array
    for (String l : languages) {
      System.out.println("Я хочу выучить " + l);
    }

    /* size for collection
    for (int i = 0; i < languages.size(); i++) {
      System.out.println("Я хочу выучить " + languages.get(i));
    }*/
  }
}
