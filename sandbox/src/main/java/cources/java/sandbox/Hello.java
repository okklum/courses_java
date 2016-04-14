package cources.java.sandbox;

public class Hello {

  public static void main(String[] args) {
    printText(" from the outside");

    //ДЗ №2: Создаем новые объекты
    Point p1 = new Point(2,4);
    Point p2 = new Point(3,5);

    //ДЗ №2: Выводим результат
    System.out.println("Расстояние между точками с координатами " + p1.x + " , " + p1.y + " и " + p2.x + " , " + p2.y + " = " + distance(p1,p2));


    //Создан объект с добавлением нового значения, атрибуты заполнены в конструкторе
    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.kvadr + " равна " + s.area ());

    Rectangle r = new Rectangle(4.5, 2.5);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " равна " + r.area());
  }


  //ДЗ №2: Пишем функцию для вычисления
  public static double distance (Point p1, Point p2) {
    return Math.sqrt(((p2.x - p1.x) * (p2.x - p1.x)) + ((p2.y - p1.y) * (p2.y - p1.y)));
  }

  public static void printText (String somebody){
    System.out.println("Hello" + somebody + "!");
  }
}