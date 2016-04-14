package cources.java.sandbox;

public class Hello {

  public static void main(String[] args) {
    printText(" from the outside");

    //ДЗ №2: Идея! Переделываем два объекта с двумя атрибутами в один объект с четыремя
    Point p = new Point(2,4,3,7);

    //ДЗ №2: Выводим результат
    System.out.println("Расстояние между точками с координатами " + p.x1 + " , " + p.y1 + " и "
            + p.x2 + " , " + p.y2 + " = " + p.distance());


    //Создан объект с добавлением нового значения, атрибуты заполнены в конструкторе
    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.kvadr + " равна " + s.area ());

    Rectangle r = new Rectangle(4.5, 2.5);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " равна " + r.area());
  }


  public static void printText (String somebody){
    System.out.println("Hello" + somebody + "!");
  }
}