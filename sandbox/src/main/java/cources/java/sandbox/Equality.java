package cources.java.sandbox;

import java.util.Objects;

/**
 * Created by alisa on 25.04.2016.
 */
public class Equality {

  public static void main (String [] args) {
    String s1 = "firefox";
    String s2 = new String(s1);

    String s3 = "fire" + "fox";

    //Физическое сравнение: сравниваются ссылки на объект, тут они разные
    System.out.println(s1 == s2);
    //Логическое сравнение: сравнивается содержимое объектов, одинаковое
    System.out.println(Objects.equals(s1, s2));

    //Ссылки на объекты идентичны,т.к. переменные с одинаковой литеральной строкой ссылаются на 1 физ.объект
    System.out.println(s1 == s3);
  }

}
