package logic;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class Bot_move {
  int [][] fields;
  int destinationX, destinationY;
 // int x,y;  //kóry pionek będzie ruszany
  ArrayList path;
  PossibleMove [][] possible_move;
  int gain_of_distance;
  Bot_move(int [][] fields){
    this.fields = fields;

  }
  /**
   * oblicza drogę
   * @param x współrżędna początkowa
   * @param y współrzędna początkowa
   * @param destinationX współrzędna końcowa
   * @param destinationY  współrzędna końcowa
   * @return obliczona droga z uwzględnieniem zasad gry (można na skos w jedną stronę)
   */
  public int calculate_distance(int x, int y, int destinationX, int destinationY){
    System.out.println("droga od " + x + " " + y + " do " + destinationX + " " + destinationY);
    int skos =min (abs(x-destinationX), abs(y - destinationY));
    if( (x-destinationX >0 && y - destinationY >0 ) || (x-destinationX <0 && y - destinationY <0 ))//skosy
      return abs(x-destinationX) + abs(y - destinationY) - skos;
    return abs(x-destinationX) + abs(y - destinationY);
  }

  public void setDestinationX(int destinationX) {
    this.destinationX = destinationX;
  }

  public void setDestinationY(int destinationY) {
    this.destinationY = destinationY;
  }


  /**
   * @param x początkowa pozycja
   * @param y początkowa pozycja
   * @param check_x współrzędna x do której szukamy ściżki
   * @param check_y współrzędna y do krórej szukamy ścieżki
   * @return lista postaci ((x,y),(x,y) ...)
   *   gdzie pierwsze wystapienie to szukana pozycja
   *   a ostatnia to obecna pozycja
   */
  public ArrayList getPath(int x, int y, int check_x, int check_y) { //
    ArrayList<Integer> path = new ArrayList<>();
    path.add(check_x);
    path.add(check_y);
    int help_x, help_y;
    if (possible_move [check_x][check_y].possible && x != check_x && y!= check_y){
      //System.out.println(x + " " + y);
     // System.out.println("LICZE sciezko do " + check_x + " " + check_y);
      while(check_x != x && check_y != y){
        // System.out.println("o kurwa");
        help_x = possible_move[check_x][check_y].getPreviousX();
        help_y = possible_move[check_x][check_y].getPreviousY();
        //System.out.println(help_x + " " + help_y);
        check_x = help_x;
        check_y = help_y;
        path.add(help_x);
        path.add(help_y);
      }
    }

    return path;
  }
  public int calculate_best_move_of_one_checker (int x, int y){ //wejście wspórzędne pkt z któego sięrusamy
    CheckMove checkMove = new CheckMove();
    checkMove.setFields(fields);
    checkMove.setXY(x,y);
    possible_move = checkMove.getMove_option();
    int destination = calculate_distance(x,y,destinationX,destinationY);

    System.out.println("DROGA PRZED: " + destination);
   // boolean possible_any_move = false;
    int profit=0, max_profit=0, best_moveX =0, best_moveY=0;
    for (int i = 0; i< fields.length ;i ++) {
      for (int j = 0; j < fields.length; j++) {
        if (possible_move[i][j].possible) {
        //  possible_any_move = true;
          int pom = calculate_distance(i,j,destinationX,destinationY);
          System.out.println( " droga: " + pom);
          profit = destination - pom;
          if (profit > max_profit){
            max_profit = profit;
            best_moveX = i;
            best_moveY = j;
          }
        }
      }
    }
    //System.out.println("luz po");
    //if(possible_any_move) {
      path = getPath(x,y,best_moveX,best_moveY);
     // System.out.println("TAK");
      return max_profit;
   // }
    //else return -1;
  }


//  public void setX(int x) {
//    this.x = x;
//  }
//
//  public void setY(int y) {
//    this.y = y;
//  }

  public ArrayList<Integer> getPath() {
    return path;
  }
}
