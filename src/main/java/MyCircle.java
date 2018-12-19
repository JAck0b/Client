import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class MyCircle extends Circle {
  /**
   * Coordinate x.
   */
  private int x;
  /**
   * Coordinate y.
   */
  private int y;
  /**
   * Stroke color of circle.
   */
  private int strokeColor;
  /**
   * If this circle is clicked true.
   */
  private boolean active = false;

  int getX() {
    return x;
  }

  void setX(int x) {
    this.x = x;
  }

  int getY() {
    return y;
  }

  void setY(int y) {
    this.y = y;
  }

  /**
   * This method fills all circles.
   * @param fillColor Value of color.
   */
  void setFillColor(int fillColor) {
    switch (fillColor) {
      case 1:
        setFill(Color.LIGHTGRAY);
        break;
      case 2:
        setFill(Color.DARKBLUE);
        break;
      case 3:
        setFill(Color.CRIMSON);
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
        setFill(Color.DEEPSKYBLUE);
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
        setFill(Color.RED);
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

  /**
   * This method sets special stroke's color in bases.
   */
  void setHomes() {
    switch (strokeColor) {
      case 1:
        setStrokeWidth(1);
        setStroke(Color.BLACK);
        break;
      case 2:
        setStrokeWidth(3);
        setStroke(Color.DARKBLUE);
        break;
      case 3:
        setStrokeWidth(3);
        setStroke(Color.RED);
        break;
      case 4:
        setStrokeWidth(3);
        setStroke(Color.DARKGREEN);
        break;
      case 5:
        setStrokeWidth(3);
        setStroke(Color.DARKCYAN);
        break;
      case 6:
        setStrokeWidth(3);
        setStroke(Color.INDIGO);
        break;
      case 7:
        setStrokeWidth(3);
        setStroke(Color.SADDLEBROWN);
        break;
    }
  }

  void setStrokeColor(int strokeColor) {
    this.strokeColor = strokeColor;
  }

  int getStrokeColor() {
    return this.strokeColor;
  }

  boolean isActive() {
    return active;
  }

  void setActive(boolean active) {
    this.active = active;
  }
}
