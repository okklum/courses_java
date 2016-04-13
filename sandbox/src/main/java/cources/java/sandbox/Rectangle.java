package cources.java.sandbox;

/**
 * Created by alisa on 11.04.2016.
 */
public class Rectangle {

  public double a;
  public  double b;


  public Rectangle (double a, double b) {
    this.a = a;
    this.b = b;
  }

  //Перенос ф-ции в др. класс, убираем static и параметр - ссылка на объект через this
  public double area (){
    return this.a * this.b;
  }
}