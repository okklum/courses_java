package cources.java.sandbox;

/**
 * Created by alisa on 13.04.2016.
 */
public class Point {

  //ДЗ №2: Прописываем атрибуты, которые соответствуют координатам точки на плоскости
  public double x1;
  public double y1;
  public double x2;
  public double y2;

   //ДЗ №2: Добавляем конструктор
  public Point (double x1, double y1, double x2, double y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  //ДЗ №2: Пишем функцию для вычисления
  public double distance () {
    return Math.sqrt(((this.x2 - this.x1) * (this.x2 - this.x1)) + ((this.y2 - this.y1) * (this.y2 - this.y1)));
    }
   }

