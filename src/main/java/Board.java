import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

//TODO zrobić z tego NormalBoard, stworzyć abstrakcyjną klasę / interface Board dla zbioru metod oraz komunikacji z serwerem

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
   * Table with up-to-date representation of the board.
   */
  private static int[][] tmpTable;

  @FXML
  Pane pane;
  @FXML
  VBox layout;
  @FXML
  TextField status;
  @FXML
  Button ready;
  @FXML
  Button finish;

  // Ta metoda przypisuje tablicę
  static void writeTmpTable(int[][] table) {
    tmpTable = table;
  }

  //TODO Dopisanie odpowiadających wartośći w tabeli z serwera
  @FXML
  public void initialize() {
    finish.setDisable(true);
    corner = new MyCircle[6][10];
    for (int i = 0; i < sizeOfTable; i++) {
      myCircles[i] = new MyCircle[coordinates[i][1]];

      for (int j = 0; j < myCircles[i].length; j++) {
        myCircles[i][j] = new MyCircle();
      }
    }
    refresh();
    //TODO Napisanie handlera dla MyCircle
    colorCorner();
//        myCircles[i][j].setOnMouseClicked(e->{
//        });

    draw();

  }

  //TODO czy potrzebne są współżędne w MyCircle i usunąć nieptrzebne odwołania
  private void refresh() {
    for (int i = 0; i < sizeOfTable; i++) {
      int k = 0;
      while (tmpTable[k][i] == 0) {
        k++;
      }
      for (int j = 0; j < myCircles[i].length; j++) {

        myCircles[i][j].setColor(tmpTable[k][i]);
        myCircles[i][j].setX(k);
        myCircles[i][j].setY(i);
        k++;
      }
    }
  }

  private void draw() {
    int x;
    int y = 40;
    int quantity;
    int r = 20;
    for (int i = 0; i < sizeOfTable; i++) {
      x = coordinates[i][0] * 10;
      quantity = coordinates[i][1];
      for (int j = 0; j < quantity; j++) {
        myCircles[i][j].setCenterX(x);
        myCircles[i][j].setCenterY(y);
        myCircles[i][j].setRadius(r);
        pane.getChildren().add(myCircles[i][j]);
        x += 40;
      }
      y += 40;
    }
  }

  @FXML
  public void finishHandler() {

  }

  @FXML
  public void readyHandler() {
    String input;
    try {
      input = in.readLine();
      status.setText(input);
    } catch (IOException e) {
      e.printStackTrace();
    }

    finish.setDisable(false);
    ready.setDisable(true);
  }

  private void colorCorner() {
    initCorner();
    for (int j = 0; j < 6; j++) {
      for (int i = 0; i < 10; i++) {
        corner[j][i].setHomes();
      }
    }
  }

  private void initCorner() {

    corner[3][0] = myCircles[0][0];
    corner[3][1] = myCircles[1][0];
    corner[3][2] = myCircles[1][1];
    corner[3][3] = myCircles[2][0];
    corner[3][4] = myCircles[2][1];
    corner[3][5] = myCircles[2][2];
    corner[3][6] = myCircles[3][0];
    corner[3][7] = myCircles[3][1];
    corner[3][8] = myCircles[3][2];
    corner[3][9] = myCircles[3][3];

    corner[4][0] = myCircles[7][9];
    corner[4][1] = myCircles[6][10];
    corner[4][2] = myCircles[6][9];
    corner[4][3] = myCircles[5][11];
    corner[4][4] = myCircles[5][10];
    corner[4][5] = myCircles[5][9];
    corner[4][6] = myCircles[4][12];
    corner[4][7] = myCircles[4][11];
    corner[4][8] = myCircles[4][10];
    corner[4][9] = myCircles[4][9];

    corner[5][0] = myCircles[9][9];
    corner[5][1] = myCircles[10][10];
    corner[5][2] = myCircles[10][9];
    corner[5][3] = myCircles[11][11];
    corner[5][4] = myCircles[11][10];
    corner[5][5] = myCircles[11][9];
    corner[5][6] = myCircles[12][12];
    corner[5][7] = myCircles[12][11];
    corner[5][8] = myCircles[12][10];
    corner[5][9] = myCircles[12][9];

    corner[0][0] = myCircles[16][0];
    corner[0][1] = myCircles[15][0];
    corner[0][2] = myCircles[15][1];
    corner[0][3] = myCircles[14][0];
    corner[0][4] = myCircles[14][1];
    corner[0][5] = myCircles[14][2];
    corner[0][6] = myCircles[13][0];
    corner[0][7] = myCircles[13][1];
    corner[0][8] = myCircles[13][2];
    corner[0][9] = myCircles[13][3];

    corner[1][0] = myCircles[9][0];
    corner[1][1] = myCircles[10][0];
    corner[1][2] = myCircles[10][1];
    corner[1][3] = myCircles[11][0];
    corner[1][4] = myCircles[11][1];
    corner[1][5] = myCircles[11][2];
    corner[1][6] = myCircles[12][0];
    corner[1][7] = myCircles[12][1];
    corner[1][8] = myCircles[12][2];
    corner[1][9] = myCircles[12][3];

    corner[2][0] = myCircles[7][0];
    corner[2][1] = myCircles[6][0];
    corner[2][2] = myCircles[6][1];
    corner[2][3] = myCircles[5][0];
    corner[2][4] = myCircles[5][1];
    corner[2][5] = myCircles[5][2];
    corner[2][6] = myCircles[4][0];
    corner[2][7] = myCircles[4][1];
    corner[2][8] = myCircles[4][2];
    corner[2][9] = myCircles[4][3];
  }

  public void setOut(PrintWriter out) {
    this.out = out;
  }

  public void setIn(BufferedReader in) {
    this.in = in;
  }
}
