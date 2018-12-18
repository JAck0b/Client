import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MyCircle extends Circle {
  private int x;
  private int y;
  private int fillColor;
  private int strokeColor;
  private boolean active = false;

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

  public int getFillColor() {
    return fillColor;
  }

  public void setFillColor(int fillColor) {
    this.fillColor = fillColor;
    switch (fillColor) {
      case 1:
        setFill(Color.LIGHTGRAY);
        break;
      case 2:
        setFill(Color.DARKBLUE);
        break;
      case 3:
        setFill(Color.RED);
        break;
      case 4:
        setFill(Color.DARKGREEN);
        break;
      case 5:
        setFill(Color.DARKCYAN);
        break;
      case 6:
        setFill(Color.INDIGO);
        break;
      case 7:
        setFill(Color.SADDLEBROWN);
        break;
      case 20:
        setFill(Color.LIGHTBLUE);
        break;
      case 30:
        setFill(Color.LIGHTCORAL);
        break;
      case 40:
        setFill(Color.LIGHTGREEN);
        break;
      case 50:
        setFill(Color.PALETURQUOISE);
        break;
      case 60:
        setFill(Color.ORCHID);
        break;
      case 70:
        setFill(Color.SANDYBROWN);
        break;
      case 200:
        setFill(Color.CORNFLOWERBLUE);
        break;
      case 300:
        setFill(Color.CRIMSON);
        break;
      case 400:
        setFill(Color.MEDIUMSEAGREEN);
        break;
      case 500:
        setFill(Color.TURQUOISE);
        break;
      case 600:
        setFill(Color.DARKVIOLET);
        break;
      case 700:
        setFill(Color.DARKGOLDENROD);
        break;
    }
  }

  public void setHomes() {
    switch (strokeColor) {
      case 1:
        setStroke(Color.BLACK);
        break;
      case 2:
        setStroke(Color.DARKBLUE);
        break;
      case 3:
        setStroke(Color.RED);
        break;
      case 4:
        setStroke(Color.DARKGREEN);
        break;
      case 5:
        setStroke(Color.DARKCYAN);
        break;
      case 6:
        setStroke(Color.INDIGO);
        break;
      case 7:
        setStroke(Color.SADDLEBROWN);
        break;
    }
  }

  public void setStrokeColor(int strokeColor) {
    this.strokeColor = strokeColor;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
