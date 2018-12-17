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
    switch (color) {
      case 1:
        setFill(Color.LIGHTGRAY);
        break;
      case 2:
        setFill(Color.DARKBLUE);
        break;
      case 3:
        setFill(Color.DARKRED);
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
    switch (color) {
      case 2:
        setStroke(Color.DARKBLUE);
        break;
      case 3:
        setStroke(Color.RED);
        break;
      case 4:
        setStroke(Color.GREEN);
        break;
      case 5:
        setStroke(Color.CORNFLOWERBLUE);
//        System.out.println("Zmienione");
        break;
      case 6:
        setStroke(Color.GRAY);
        break;
      case 7:
        setStroke(Color.YELLOW);
        break;
    }
  }

}
