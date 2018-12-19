import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MyCircleTest {

  MyCircle myCircle;

  public void setUpMock() {
    myCircle = mock(MyCircle.class);
  }

  public void setUpClass() {
    myCircle = new MyCircle();
  }

  @Test
  public void getX() {
    setUpMock();
    myCircle.getX();
    verify(myCircle).getX();
  }

  @Test
  public void getY() {
    setUpMock();
    myCircle.getY();
    verify(myCircle).getY();
  }

  @Test
  public void setX() {
    setUpClass();
    myCircle.setX(5);
    assertEquals(5, myCircle.getX());
  }

  @Test
  public void setY() {
    setUpClass();
    myCircle.setY(15);
    assertEquals(15, myCircle.getY());
  }

  @Test
  public void setFillColor() {
    setUpClass();
    myCircle.setFillColor(3);
    assertEquals(Color.RED, myCircle.getFill());
  }

  @Test
  public void setHomes() {
    setUpClass();
    myCircle.setStrokeColor(1);
    myCircle.setHomes();
    assertEquals((double)1, (double)myCircle.getStrokeWidth(), 0.001);
    assertEquals(Color.BLACK, myCircle.getStroke());
  }

  @Test
  public void setStrokeColor() {
    setUpClass();
    myCircle.setStrokeColor(1);
    assertEquals(1, myCircle.getStrokeColor());
  }

  @Test
  public void getStrokeColor() {
    setUpMock();
    myCircle.getStrokeColor();
    verify(myCircle).getStrokeColor();
  }

  @Test
  public void isActive() {
    setUpClass();
    myCircle.setActive(true);
    assertTrue(myCircle.isActive());
  }

  @Test
  public void setActive() {
    setUpMock();
    myCircle.setActive(true);
    verify(myCircle).setActive(true);
    myCircle.setActive(false);
    verify(myCircle).setActive(false);

  }
}