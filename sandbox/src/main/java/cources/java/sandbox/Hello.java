package cources.java.sandbox;

public class Hello {

  public static void main(String[] args) {
    printText(" from the outside");
    System.out.println("Для n = 5 результат " + Primes.isPrime(5) + "; res = " + Primes.res);

    Point p1 = new Point(2,4);
    Point p2 = new Point(3,7);

    System.out.println("Расстояние между точками с координатами " + p1.x + " , " + p1.y + " и "
            + p2.x + " , " + p2.y + " = " + Point.distance(p1,p2));


    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.kvadr + " равна " + s.area ());

    Rectangle r = new Rectangle(4.5, 2.5);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " равна " + r.area());
  }


  public static void printText (String somebody){
    System.out.println("Hello" + somebody + "!");
  }
}