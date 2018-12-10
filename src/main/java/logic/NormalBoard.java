package logic;

import java.util.ArrayList;

public class NormalBoard {
  private final int size = 4;
  public int[][] fields;
 // private int[][] base;

  public int[][] getFields() {
    return fields;
  }

  public NormalBoard(int number_of_players) {
    /*fields = new int[][]{
      {0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {4, 4, 4, 4, 1, 1, 1, 1, 1, 6, 6, 6, 6, 0, 0, 0, 0},
      {0, 4, 4, 4, 1, 1, 1, 1, 1, 1, 6, 6, 6, 0, 0, 0, 0},
      {0, 0, 4, 4, 1, 1, 1, 1, 1, 1, 1, 6, 6, 0, 0, 0, 0},
      {0, 0, 0, 4, 1, 1, 1, 1, 1, 1, 1, 1, 6, 0, 0, 0, 0},
      {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
      {0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 7, 0, 0, 0},
      {0, 0, 0, 0, 3, 3, 1, 1, 1, 1, 1, 1, 1, 7, 7, 0, 0},
      {0, 0, 0, 0, 3, 3, 3, 1, 1, 1, 1, 1, 1, 7, 7, 7, 0},
      {0, 0, 0, 0, 3, 3, 3, 3, 1, 1, 1, 1, 1, 7, 7, 7, 7},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0}
    };*/
    fields = new int[][]{
      {0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {5, 5, 5, 5, 1, 1, 1, 1, 1, 3, 3, 3, 3, 0, 0, 0, 0},
      {0, 5, 5, 5, 1, 1, 1, 1, 1, 1, 3, 3, 3, 0, 0, 0, 0},
      {0, 0, 5, 5, 1, 1, 1, 1, 1, 1, 1, 3, 3, 0, 0, 0, 0},
      {0, 0, 0, 5, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0},
      {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
      {0, 0, 0, 0, 6, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 0},
      {0, 0, 0, 0, 6, 6, 1, 1, 1, 1, 1, 1, 1, 2, 2, 0, 0},
      {0, 0, 0, 0, 6, 6, 6, 1, 1, 1, 1, 1, 1, 2, 2, 2, 0},
      {0, 0, 0, 0, 6, 6, 6, 6, 1, 1, 1, 1, 1, 2, 2, 2, 2},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0}
    };
   /* base = new int[][]{
      {0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {7, 7, 7, 7, 0, 0, 0, 0, 0, 3, 3, 3, 3, 0, 0, 0, 0},
      {0, 7, 7, 7, 0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0},
      {0, 0, 7, 7, 0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0},
      {0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
      {0, 0, 0, 0, 6, 6, 0, 0, 0, 0, 0, 0, 0, 4, 4, 0, 0},
      {0, 0, 0, 0, 6, 6, 6, 0, 0, 0, 0, 0, 0, 4, 4, 4, 0},
      {0, 0, 0, 0, 6, 6, 6, 6, 0, 0, 0, 0, 0, 4, 4, 4, 4},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0}
    };*/
    prepare_fields(number_of_players);
  }
  public void change_fields(int previous, int succesor){
    for (int i = 0; i< fields.length ;i ++)
      for (int j = 0; j< fields.length; j++ )
        if(fields[i][j] == previous)
          fields[i][j] = succesor;
  }
  // TODO do sprawdzenia w zasadach którzy gracze grają
  public void prepare_fields(int number_of_players){
    switch (number_of_players){
      case 2: {
        change_fields(7,1);
        change_fields(6,1);
        change_fields(4,1);
        change_fields(3,1);
        break;
      }
      case 3: {
        change_fields(6,1);
        change_fields(4,1);
        change_fields(3,1);
        break;
      }
      case 4: {
        change_fields(6,1);
        change_fields(3,1);
        break;
      }
      case 5: {
        change_fields(6,1);
        break;
      }
    }
  }

  public void printArray() {    //odrotne wyświetlannie
    for (int y = 0; y < fields.length; y++) {
      for (int x = 0; x < fields.length; x++) {
        System.out.print(fields[x][y] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    NormalBoard nb = new NormalBoard(6);
    nb.fields[9][12] =2;
//    nb.fields[4][1] =9;
//    nb.fields[5][1] =2;

    nb.printArray();
    CheckMove checkMove = new CheckMove();
    checkMove.setFields(nb.fields);
   // checkMove.setXY(12,14);
//    System.out.println("Czy można na : " + checkMove.check_move(4,4));
 //   checkMove.printArray();
//    checkMove.printPath(checkMove.getPath(4,4));

      Bot bot = new Bot(checkMove.fields);
      bot.setId(2);
     // bot.find_destinaionXY();
   // System.out.println("DEST: " + bot.destinationX + " " +bot.destinationY);
      bot.calculate_best_move();

// Bot_move bot_move = new Bot_move(checkMove.fields);
//    bot_move.setDestinationX(4);
//    bot_move.setDestinationY(0);
//    System.out.println("MAX PROFIT: " + bot_move.calculate_best_move_of_one_checker(12,14) );

//    System.out.println(bot.calculate_distance(12,16,4,0));
//    System.out.println(bot.calculate_distance(4,12,12,4));
//    System.out.println(bot.calculate_distance(16,12,0,4));
    //todo sprawdzić odległosć w skrajnych przypadkach ????
  }
}