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
    Point n = new Point(1,1,1,1);
    Assert.assertEquals(n.distance(), 0.0);
  }

  @Test
  public void checkSum1() {
    Point p = new Point(2,4,3,7);
    //проверяем один и тот же результат разными способами
    Assert.assertEquals(p.distance(), 3.1622776601683795);
    Assert.assertEquals(p.distance(), Math.sqrt(((p.x2 - p.x1) * (p.x2 - p.x1))+((p.y2 - p.y1)*(p.y2 - p.y1))));
  }
}
