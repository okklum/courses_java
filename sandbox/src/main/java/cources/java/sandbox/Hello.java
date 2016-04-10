package cources.java.sandbox;

public class Hello {

  public static void main(String[] args) {
    printText(" from the outside");
    printText(", World");
    printText(", Alisa");

    int l = 5;
    System.out.println("Площадь квадрата со стороной " + l + " равна " + area (l));

    double a = 4.5;
    double b = 2.5;
    System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " равна " + area(a,b));
  }

  public static void printText (String somebody){
    System.out.println("Hello" + somebody + "!");
  }

  public static int area (int kvadr) {
    return kvadr*kvadr;
  }

  public static double area (double a, double b){
    return a * b;
  }
}