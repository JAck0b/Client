import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.Bot;
import logic.CheckMove;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Board {
  /**
   * Input from server.
   */
  private BufferedReader in;

  /**
   * Output into server.
   */
  private PrintWriter out;
  private MyCircle[][] myCircles = new MyCircle[17][];
  private final int[][] coordinates = new int[][] {
    {28, 1}, {26, 2}, {24, 3}, {22, 4}, {4, 13}, {6, 12}, {8, 11}, {10, 10}, {12, 9},
    {10, 10}, {8, 11}, {6, 12}, {4, 13}, {22, 4}, {24, 3}, {26, 2}, {28, 1} };
  private MyCircle[][] corner;

  private static int[][] fields;
  private int [] number_of_skip_by_id = {0,0,0,0,0,0};
  private boolean [] still_in_game = {true,true,true,true,true,true};
  private int totalsteps =0;  //number of move in game
  private ArrayList<int[][]> bases;
  private ArrayList <Integer> winners = new ArrayList<>();
  static int number_of_players;


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

  public void setOut(PrintWriter out) {
    this.out = out;
  }

  public void setIn(BufferedReader in) {
    this.in = in;
  }
  @FXML
  public void finishHandler() {

  }
  // Ta metoda przypisuje tablicę
  static void writefields(int[][] table) {
    fields = table;
//    for (int y = 0; y < 17; y++) {
//      for (int x = 0; x < 17; x++) {
//        System.out.print(fields[x][y] + " ");
//      }
//      System.out.println();
//    }
  }
  private void add_bases(){
    bases = new ArrayList<>();
    int [][] base2 = new int[][]{ //pionki z nr2 dążą do ... i symetrycznie 7 do x=y y=x
      {4,0},{4,1},{5,1},{4,2},{5,2},{6,2},{7,3},{4,3},{6,3},{5,3}
    };
    bases.add(base2);

    int [][] base3 = new int[][]{   //symetrai x=y y=x dla 6
      {12,4},{11,4},{12,5},{11,5},{10,4},{12,6},{9,4},{12,7},{10,5},{11,6}
    };
    bases.add(base3);

    int [][] base4 = new int[][]{   //symetrai x=y y=x dla 5
      {16,12},{15,12},{15,11},{14,12},{14,11},{14,10},{13,12},{13,9},{13,11},{13,10}
    };
    bases.add(base4);
  }
  private boolean all_in_base(int id){
    int checkX =0, checkY =0;
    for(int i=0; i < 10 ; i++ ){
      switch (id) {
        case 2: {
          checkX = bases.get(0)[i][0];
          checkY = bases.get(0)[i][1];
          break;
        }
        case 3: {
          checkX = bases.get(1)[i][0];
          checkY = bases.get(1)[i][1];
          break;
        }
        case 4: {
          checkX = bases.get(2)[i][0];
          checkY = bases.get(2)[i][1];
          break;
        }
        case 5: {
          checkX = bases.get(2)[i][1];
          checkY = bases.get(2)[i][0];
          break;
        }
        case 6: {
          checkX = bases.get(1)[i][1];
          checkY = bases.get(1)[i][0];
          break;
        }
        case 7: {
          checkX = bases.get(0)[i][1];
          checkY = bases.get(0)[i][0];
          break;
        }
      }
      if (fields[checkX][checkY] != id)
        return false;

    }
    return true;

  }

  private void refresh() {
    for (int i = 0; i < fields.length; i++) {
      int k = 0;
      while (fields[k][i] == 0) {
        k++;
      }
      for (int j = 0; j < myCircles[i].length; j++) {

        myCircles[i][j].setColor(fields[k][i]);
        myCircles[i][j].setX(k);
        myCircles[i][j].setY(i);
        k++;
      }
    }
  }
  //TODO Dopisanie odpowiadających wartośći w tabeli z serwera
  @FXML
  public void initialize() {

    finish.setDisable(true);
    corner = new MyCircle[6][10];
    for (int i = 0; i < fields.length; i++) {
      myCircles[i] = new MyCircle[coordinates[i][1]];

      for (int j = 0; j < myCircles[i].length; j++) {
        myCircles[i][j] = new MyCircle();
        myCircles[i][j].setOnMouseClicked(e->{

          System.out.println("x = " + ((MyCircle)e.getSource()).getX());
          System.out.println("y = " + ((MyCircle)e.getSource()).getY());
        });
      }
    }
    refresh();
    //TODO Napisanie handlera dla MyCircle
    colorCorner();


    draw();
  }

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
        pane.getChildren().add(myCircles[i][j]);
        x += 40;
      }
      y += 40;
    }
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
  @FXML
  public void lineCommand(ActionEvent e) {
   // CheckMove checkMove = new CheckMove(true);
    //checkMove.setFields(fields);
    Bot bot = new Bot(fields,true);
    add_bases();
    bot.setBases(bases);
    int steps = totalsteps %number_of_players;
    int id = 0;
    //rusza botami w kolejności zegara
    switch (number_of_players){
      case (6):{
        id=steps +2;
        break;
      }
      case (5):{
        if(steps ==4)
          id = 7;
        else
          id = steps +2;
        break;
      }
      case (4):{
        if(steps ==0)
          id = 2;
        else if(steps == 3)
          id = 7;
        else
          id = steps +3;
        }
        break;
      case (3): {
        if(steps ==0)
          id = 2;
        else if(steps == 1)
          id = 5;
        else
          id = 7;
        break;
      }
      case (2): {
        if(steps ==0)
          id = 2;
        else
          id = 5;
      }
    }
    System.out.println(id);
    if(still_in_game[id-2]){
      bot.setId(id);
      bot.setSteps_in_game(totalsteps/number_of_players);
      bot.calculate_best_move();
      //czy ruch pomijany
      if(!bot.isBot_skip_move()){
        ArrayList<Integer> p; //path
        p = bot.getPath_best_move();


        //aktualizacja pola na które się ruszył pionkek ustalamy id pionka na tym polu
        //ustawianie kolorów ścieżki
        fields[p.get(0)][p.get(1)] = id;
        for(int i=2; i< p.size();i=i+2)
          fields[p.get(i)][p.get(i+1)] = (id)*10;


        //refresh
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
        //ścieżka wraca jako normalne pole
        for(int i=2; i< p.size();i=i+2){
          fields[p.get(i)][p.get(i+1)] = 1;
        }
        if(all_in_base(id)){
          still_in_game[id-2] = false;
          winners.add(id);
        }
      }
      else {
        number_of_skip_by_id [id-2] = number_of_skip_by_id [id-2] + 1;
        // gdy 3 skipy dany plater nie gra
        if(number_of_skip_by_id [id-2] >= 3) {
          still_in_game[id-2] = false;
        }
      }
    }
    totalsteps++;
    //todo testy

    for(int i = 0; i < 6; i++)
      System.out.print(number_of_skip_by_id[i] + " ");
    System.out.println();
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


//  @FXML
//  public void endHandler(ActionEvent e) {
//    Stage stage = (Stage) layout.getScene().getWindow();
//    stage.close();
//  }
}
