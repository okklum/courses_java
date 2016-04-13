package cources.java.sandbox;

public class Hello {

  public static void main(String[] args) {
    printText(" from the outside");

    //ДЗ №2: Создаем новые объекты
    Point p1 = new Point();
    Point p2 = new Point();

    //ДЗ №2: Добавляем координаты, пока без конструктора
    p1.x = 2;
    p1.y = 4;
    p2.x = 3;
    p2.y = 5;
    //ДЗ №2: Выводим результат
    System.out.println("Расстояние между точками равно " + distance(p1,p2));


    //Создан объект с добавлением нового значения, атрибуты заполнены в конструкторе
    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.kvadr + " равна " + s.area ());

    Rectangle r = new Rectangle(4.5, 2.5);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " равна " + r.area());
  }

  //ДЗ №2: Пишем функцию для вычисления
  public static double distance(Point p1, Point p2) {
    return Math.sqrt(((p2.x - p1.x) * (p2.x - p1.x)) + ((p2.y - p1.y) * (p2.y - p1.y)));
  }

  public static void printText (String somebody){
    System.out.println("Hello" + somebody + "!");
  }
}