package cources.java.sandbox;

public class Hello {

  public static void main(String[] args) {
    printText(" from the outside");
    System.out.println("Для n = 5 результат " + Primes.isPrime(5) + "; res = " + Primes.res);

    Point p = new Point(2,4,3,7);

    System.out.println("Расстояние между точками с координатами " + p.x1 + " , " + p.y1 + " и "
            + p.x2 + " , " + p.y2 + " = " + p.distance());


    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.kvadr + " равна " + s.area ());

    Rectangle r = new Rectangle(4.5, 2.5);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " равна " + r.area());
  }


  public static void printText (String somebody){
    System.out.println("Hello" + somebody + "!");
  }
}