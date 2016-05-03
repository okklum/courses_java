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

    /* вариант с искуственной переменной-счетчиком; Для массива атрибут length
    for (int i = 0; i < langs.length; i++) {
      System.out.println("Я хочу выучить " + langs[i]);
    }*/

    /* Преобразование массива в список
    List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("PHP");*/
    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");


    //конструкция цикла для перебора элементов коллекции или массива
    for (String l : languages) {
      System.out.println("Я хочу выучить " + l);
    }

    /* конструкция цикла для перебора элементов коллекции; для коллекции атрибут size
    for (int i = 0; i < languages.size(); i++) {
      System.out.println("Я хочу выучить " + languages.get(i));
    }*/
  }
}
