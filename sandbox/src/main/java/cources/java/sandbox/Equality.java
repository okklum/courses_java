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

    //Fizicheskoe sravnenie: sravnivayutsya ssylki na ob"ekt, tut oni raznye
    System.out.println(s1 == s2);
    //Logicheskoe sravnenie: sravnivaetsya soderzhimoe ob"ektov, odinakovoe
    System.out.println(Objects.equals(s1, s2));

    //Ssylki na ob"ekty identichny,t.k. peremennye s odinakovoj literal'noj strokoj ssylayutsya na 1 fiz.ob"ekt
    System.out.println(s1 == s3);
  }

}
