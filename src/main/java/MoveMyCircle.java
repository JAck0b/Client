import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class MoveMyCircle implements EventHandler {
  private static MyCircle myCircleStart;
  private static MyCircle myCircleEnd;
  private static boolean duringAction;

  public MoveMyCircle() {
    super();
    duringAction = false;
  }

  public static MyCircle getMyCircleStart() {
    return myCircleStart;
  }

  public static void setMyCircleStart(MyCircle myCircleStart) {
    MoveMyCircle.myCircleStart = myCircleStart;
  }

  public static MyCircle getMyCircleEnd() {
    return myCircleEnd;
  }

  public static void setMyCircleEnd(MyCircle myCircleEnd) {
    MoveMyCircle.myCircleEnd = myCircleEnd;
  }


  public boolean isDuringAction() {
    return duringAction;
  }

  public void setDuringAction(boolean duringAction) {
    this.duringAction = duringAction;
  }

  @Override
  public void handle(Event event) {
    if (!isDuringAction() && ((MyCircle)event.getSource()).getColor() != 1) {
      setDuringAction(true);
      setMyCircleStart((MyCircle)event.getSource());
    } else {
      setDuringAction(false);
      setMyCircleEnd((MyCircle)event.getSource());
    }
  }

}
