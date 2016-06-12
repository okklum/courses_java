package cources.java.sandbox;

/**
 * Created by alisa on 11.04.2016.
 */
public class Square {

  public int kvadr;

  //add constructor
  public Square (int kvadr){
    //init new (parameter) Ob. in constructor: values filling
    this.kvadr = kvadr;
  }

  //move method in other class, delete "static" & attribut (add "this")
  public int area () {
    return this.kvadr * this.kvadr;
  }

}
