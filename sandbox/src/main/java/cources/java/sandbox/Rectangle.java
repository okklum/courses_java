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

  //move method in other class, delete "static" & attribut (add "this")
  public double area (){
    return this.a * this.b;
  }
}