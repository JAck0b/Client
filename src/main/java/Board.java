import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import logic.Bot;
import logic.CheckMove;
import logic.NormalBoard;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {
  private MyCircle[][] myCircles = new MyCircle[17][];
  private final int[][] coordinates = new int[][] {
    {28, 1}, {26, 2}, {24, 3}, {22, 4}, {4, 13}, {6, 12}, {8, 11}, {10, 10}, {12, 9},
    {10, 10}, {8, 11}, {6, 12}, {4, 13}, {22, 4}, {24, 3}, {26, 2}, {28, 1} };
  private MyCircle[][] corner;
  private static int[][] fields;
  ArrayList<Integer> p;
  int j=0;
  @FXML
  Pane pane;
  @FXML
  HBox layout;

  // Ta metoda przypisuje tablicę
  public static void writefields(int[][] table) {
    fields = table;
//    for (int y = 0; y < 17; y++) {
//      for (int x = 0; x < 17; x++) {
//        System.out.print(fields[x][y] + " ");
//      }
//      System.out.println();
//    }
  }

  //TODO Dopisanie odpowiadających wartośći w tabeli z serwera
  @FXML
  public void initialize() {
//    corner = new MyCircle[6][10];
    for (int i = 0; i < 17; i++) {
      myCircles[i] = new MyCircle[coordinates[i][1]];
      int k = 0;
      while (fields[k][i] == 0) {
        k++;
      }
      for (int j = 0; j < myCircles[i].length; j++) {
        myCircles[i][j] = new MyCircle();
        myCircles[i][j].setColor(fields[k][i]);
        myCircles[i][j].setX(k);
        myCircles[i][j].setY(i);
//        myCircles[i][j].setOnMouseClicked(e->{
//          //TODO Pomyśleć jak powinien wyglądać Connector z serwerem
//          ConnectorWithServer.sendPoint(e.getX(), e.getY());
//        });
//        myCircles[i][j].setFill(Color.WHITE);
//        myCircles[i][j].setStroke(Color.BLACK);
        k++;
      }
    }

//    initCorner();
//    colorCorner(0, Color.DARKBLUE);
//    colorCorner(1, Color.RED);
//    colorCorner(2, Color.GREEN);
//    colorCorner(3, Color.CORNFLOWERBLUE);
//    colorCorner(4, Color.GRAY);
//    colorCorner(5, Color.YELLOW);
    draw();
  }

  private void draw() {
    int x;
    int y = 30;
    int quantity;
    int r = 20;
    for (int i = 0; i < 17; i++) {
      x = coordinates[i][0] * 10;
      quantity = coordinates[i][1];
      for (int j = 0; j < quantity; j++) {
        myCircles[i][j].setCenterX(x);
        myCircles[i][j].setCenterY(y);
        myCircles[i][j].setRadius(r);
        myCircles[i][j].setOnMouseClicked(e-> {

        });
        pane.getChildren().add(myCircles[i][j]);
        x += 40;
      }
      y += 40;
    }
  }
  @FXML
  public void lineCommand(ActionEvent e) {
    int x, y, color, quantity;
//    Scanner in  = new Scanner(System.in);
//    System.out.println("Enter quantity.");
//    quantity = in.nextInt();
//    for (int i = 0; i < quantity; i++) {
//      System.out.println("Enter coordinate x.");
//      x = in.nextInt();
//      System.out.println("Enter coordinate y.");
//      y = in.nextInt();
//      System.out.println("Enter color");
//      color = in.nextInt();
//      fields[x][y] = color;
//    }




    CheckMove checkMove = new CheckMove();
    checkMove.setFields(fields);
    Bot bot = new Bot(checkMove.fields);

   // if(j==0)
      bot.setId(j+2);
    //else
      //bot.setId(5);
    bot.calculate_best_move();
   // p.clear();
    p = bot.getPath_best_move();
    for(int i=2; i< p.size();i=i+2){
      fields[p.get(i)][p.get(i+1)] = 9;
    }
     //if(j==0)
    fields[p.get(0)][p.get(1)] = 8;
//    else
//       fields[p.get(0)][p.get(1)] =8;


    //writefields(fields);

    //checkMove.printArray();

      for (int i = 0; i < 17; i++) {
        int k = 0;
        while (fields[k][i] == 0) {
          k++;
        }
        for (int j = 0; j < myCircles[i].length; j++) {
          myCircles[i][j].setColor(fields[k][i]);

          k++;
        }
      }


    for(int i=2; i< p.size();i=i+2){
      fields[p.get(i)][p.get(i+1)] = 1;
    }
    if(j==0)
      fields[p.get(0)][p.get(1)] = 2;
    else if (j == 1)
      fields[p.get(0)][p.get(1)] = 3;
    else if (j == 2)
      fields[p.get(0)][p.get(1)] = 4;
    else if (j == 3)
      fields[p.get(0)][p.get(1)] = 5;
    else if (j == 4)
      fields[p.get(0)][p.get(1)] = 6;
    else if (j == 5)
      fields[p.get(0)][p.get(1)] = 7;


        //in.nextLine();
    j++;
    j=j%6;

  }

  private void colorCorner(int number, Color color) {
    for (int i = 0; i < 10; i++) {
      corner[number][i].setFill(color);
    }
  }

  private void initCorner() {

    corner[0][0] = myCircles[0][0];
    corner[0][1] = myCircles[1][0];
    corner[0][2] = myCircles[1][1];
    corner[0][3] = myCircles[2][0];
    corner[0][4] = myCircles[2][1];
    corner[0][5] = myCircles[2][2];
    corner[0][6] = myCircles[3][0];
    corner[0][7] = myCircles[3][1];
    corner[0][8] = myCircles[3][2];
    corner[0][9] = myCircles[3][3];

    corner[1][0] = myCircles[7][9];
    corner[1][1] = myCircles[6][10];
    corner[1][2] = myCircles[6][9];
    corner[1][3] = myCircles[5][11];
    corner[1][4] = myCircles[5][10];
    corner[1][5] = myCircles[5][9];
    corner[1][6] = myCircles[4][12];
    corner[1][7] = myCircles[4][11];
    corner[1][8] = myCircles[4][10];
    corner[1][9] = myCircles[4][9];

    corner[2][0] = myCircles[9][9];
    corner[2][1] = myCircles[10][10];
    corner[2][2] = myCircles[10][9];
    corner[2][3] = myCircles[11][11];
    corner[2][4] = myCircles[11][10];
    corner[2][5] = myCircles[11][9];
    corner[2][6] = myCircles[12][12];
    corner[2][7] = myCircles[12][11];
    corner[2][8] = myCircles[12][10];
    corner[2][9] = myCircles[12][9];

    corner[3][0] = myCircles[16][0];
    corner[3][1] = myCircles[15][0];
    corner[3][2] = myCircles[15][1];
    corner[3][3] = myCircles[14][0];
    corner[3][4] = myCircles[14][1];
    corner[3][5] = myCircles[14][2];
    corner[3][6] = myCircles[13][0];
    corner[3][7] = myCircles[13][1];
    corner[3][8] = myCircles[13][2];
    corner[3][9] = myCircles[13][3];

    corner[4][0] = myCircles[9][0];
    corner[4][1] = myCircles[10][0];
    corner[4][2] = myCircles[10][1];
    corner[4][3] = myCircles[11][0];
    corner[4][4] = myCircles[11][1];
    corner[4][5] = myCircles[11][2];
    corner[4][6] = myCircles[12][0];
    corner[4][7] = myCircles[12][1];
    corner[4][8] = myCircles[12][2];
    corner[4][9] = myCircles[12][3];

    corner[5][0] = myCircles[7][0];
    corner[5][1] = myCircles[6][0];
    corner[5][2] = myCircles[6][1];
    corner[5][3] = myCircles[5][0];
    corner[5][4] = myCircles[5][1];
    corner[5][5] = myCircles[5][2];
    corner[5][6] = myCircles[4][0];
    corner[5][7] = myCircles[4][1];
    corner[5][8] = myCircles[4][2];
    corner[5][9] = myCircles[4][3];
  }


//  @FXML
//  public void endHandler(ActionEvent e) {
//    Stage stage = (Stage) layout.getScene().getWindow();
//    stage.close();
//  }
}
