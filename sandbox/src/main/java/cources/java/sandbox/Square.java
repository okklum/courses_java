package cources.java.sandbox;

/**
 * Created by alisa on 11.04.2016.
 */
public class Square {

  public int kvadr;

  //Добавляем конструктор (в отличие от класса не имеет возвращаемого значения, но требует параметры)
  public Square (int kvadr){
    //инициализация нового (параметра) объекта в конструкторе = заполнение значениями
    this.kvadr = kvadr;
  }

  //Перенос ф-ции в др. класс, убираем static и параметр - ссылка на объект через this
  public int area () {
    return this.kvadr * this.kvadr;
  }

}
