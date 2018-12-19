import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *  Representation of common Board.
 */
public class Board {

  /**
   * Input from server.
   */
  private BufferedReader in;

  /**
   * Output into server.
   */
  private PrintWriter out;

  /**
   * Size of table which contain representation of this Board.
   */
  private final int sizeOfTable = 17;

  /**
   * Table with all fields on the board.
   */
  private MyCircle[][] myCircles = new MyCircle[sizeOfTable][];

  /**
   * Coordinates of all rows on the board.
   */
  private final int[][] coordinates = new int[][]{
    {28, 1}, {26, 2}, {24, 3}, {22, 4}, {4, 13}, {6, 12}, {8, 11}, {10, 10}, {12, 9},
    {10, 10}, {8, 11}, {6, 12}, {4, 13}, {22, 4}, {24, 3}, {26, 2}, {28, 1}};

  /**
   * Table of all homes on the board.
   */
  private MyCircle[][] corner;

  /**
   * Representation of board.
   */
  private int[][] fields = new int[][]{
    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}
  };
  /**
   * Server's socket.
   */
  private Socket socket;


  @FXML
  Pane pane;
  @FXML
  VBox layout;
  @FXML
  Label status;
  @FXML
  Button skip;
  @FXML
  Label stepCounter;
  @FXML
  Pane currentPlayer;
  @FXML
  CheckBox hints;
  @FXML
  Pane playersColor;

  /**
   * This method refresh board, set default stroke's color and fill's color.
   */
  void refresh() {
    for (int i = 0; i < myCircles.length; i++) {
      int k = 0;
      while (fields[k][i] == 0) {
        k++;
      }
      for (int j = 0; j < myCircles[i].length; j++) {
        myCircles[i][j].setFillColor(fields[k][i]);
        myCircles[i][j].setHomes();
        myCircles[i][j].setX(k);
        myCircles[i][j].setY(i);
        k++;
      }
    }
  }

  /**
   * This method initialize board.
   */
  @FXML
  public void initialize() {

    corner = new MyCircle[6][10];
    for (int i = 0; i < fields.length; i++) {
      myCircles[i] = new MyCircle[coordinates[i][1]];

      for (int j = 0; j < myCircles[i].length; j++) {
        myCircles[i][j] = new MyCircle();
        myCircles[i][j].setOnMouseClicked(e -> {
          refresh();
          int x = ((MyCircle) e.getSource()).getX();
          int y = ((MyCircle) e.getSource()).getY();
          out.println("COR " + x + " " + y);
          ((MyCircle) e.getSource()).setStrokeWidth(5);
          ((MyCircle) e.getSource()).setStroke(Color.GOLD);
          for (MyCircle[] myCircle : myCircles) {
            for (MyCircle myCircle1 : myCircle) {
              myCircle1.setActive(false);
            }
          }
          ((MyCircle) e.getSource()).setActive(true);
        });
      }
    }
    refresh();

    draw();

  }

  /**
   * This method starts daemon which listens and changes board.
   */
  void task() {
    BoardListener thread = new BoardListener(this, in, status, stepCounter, currentPlayer, hints, playersColor);
    thread.setDaemon(true);
    thread.start();
  }


  /**
   * This method draw circles on board.
   */
  private void draw() {
    int x;
    int y = 40;
    int quantity;
    int r = 20;
    for (int i = 0; i < fields.length; i++) {
      x = coordinates[i][0] * 10;
      quantity = coordinates[i][1];
      for (int j = 0; j < quantity; j++) {
        myCircles[i][j].setCenterX(x);
        myCircles[i][j].setCenterY(y);
        myCircles[i][j].setRadius(r);
        myCircles[i][j].setStroke(Color.BLACK);
        pane.getChildren().add(myCircles[i][j]);
        x += 40;
      }
      y += 40;
    }
  }


  @FXML
  public void skipHandler() {
    out.println("SKIP");
  }


  void setOut(PrintWriter out) {
    this.out = out;
  }

  PrintWriter getOut() {
    return this.out;
  }

  BufferedReader getIn() {
    return this.in;
  }

  void setIn(BufferedReader in) {
    this.in = in;
  }

  MyCircle[][] getMyCircles() {
    return myCircles;
  }

  int[][] getFields() {
    return fields;
  }

  Socket getSocket() {
    return socket;
  }

  void setSocket(Socket socket) {
    this.socket = socket;
  }


}
