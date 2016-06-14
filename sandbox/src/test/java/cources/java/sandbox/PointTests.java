package cources.java.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by alisa on 17.04.2016.
 */
public class PointTests {

  @Test
  public void checkSum() {
    //не очень точный тест, т.к. может пропустить ошибки в формуле
    Point p1 = new Point(1,1);
    Point p2 = new Point(1,1);
    Assert.assertEquals(Point.distance(p1,p2), 0.0);
  }

  @Test
  public void checkSum1() {
    Point p1 = new Point(2,4);
    Point p2 = new Point(3,7);
    //проверяем один и тот же результат разными способами
    Assert.assertEquals(Point.distance(p1,p2), Math.sqrt(10));
    Assert.assertEquals(Point.distance(p1,p2), Math.sqrt(((p2.x - p1.x) * (p2.x - p1.x))+
            ((p2.y - p1.y)*(p2.y - p1.y))));
  }
}
