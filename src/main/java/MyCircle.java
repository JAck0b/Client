import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MyCircle extends Circle {
  private int x;
  private int y;
  private int color;

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
    this.setStroke(Color.BLACK);
    switch (color) {
      case 1:
        setFill(Color.WHITE);
        break;
      case 2:
        setFill(Color.DARKBLUE);
        break;
      case 3:
        setFill(Color.GREEN);
        break;
      case 4:
        setFill(Color.GREEN);
        break;
      case 5:
        setFill(Color.CORNFLOWERBLUE);
        break;
      case 6:
        setFill(Color.GRAY);
        break;
      case 7:
        setFill(Color.YELLOW);
        break;
    }
  }

}
