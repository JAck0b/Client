package logic;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class Bot_move {
  int [][] fields;
  int destinationX, destinationY;
  ArrayList path;
  PossibleMove [][] possible_move;
  Bot_move(int [][] fields){
    this.fields = fields;

  }
  public void printArray() {
    for (int i = 0; i< fields.length ;i ++){
      for (int j = 0; j< fields.length; j++ ) {
        if (possible_move[j][i].possible)
          System.out.print(1 + " ");
        else
          System.out.print(0 + " ");
      }
      System.out.println();
    }
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
    //System.out.println("droga od " + x + " " + y + " do " + destinationX + " " + destinationY);
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
   * todo warunek czy istnieje ścieżka chyba nie potrzebny
   * @param x początkowa pozycja
   * @param y początkowa pozycja
   * @param check_x współrzędna x do której szukamy ściżki
   * @param check_y współrzędna y do krórej szukamy ścieżki
   * @return lista postaci ((x,y),(x,y) ...)
   *   gdzie pierwsze wystapienie to szukana pozycja
   *   a ostatnia to obecna pozycja
   */
  public ArrayList getPath(int x, int y, int check_x, int check_y) { //
    System.out.println(x+" "+ y+" " + check_x + " " + check_y);
    System.out.println("czemu: " + possible_move[check_x][check_y].getPreviousX() + " " + possible_move[check_x][check_y].getPreviousY());
    printArray();
    ArrayList<Integer> path = new ArrayList<>();
    int help_x, help_y;
    if (possible_move [check_x][check_y].possible && !( x == check_x && y == check_y )){
      path.add(check_x);
      path.add(check_y);

      while( !( x == check_x && y == check_y )){
        //System.out.println("problem: " + check_x + " " + check_y);
        help_x = possible_move[check_x][check_y].getPreviousX();
        help_y = possible_move[check_x][check_y].getPreviousY();
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
    checkMove.setXY(x,y); //ROBI WSZYTKO TWORZY ZERUJE I OBLICZA POSSIBLE MOVE
    possible_move = checkMove.getMove_option();
    int destination = calculate_distance(x,y,destinationX,destinationY);

    //System.out.println("DROGA PRZED: " + destination);

    int profit=0, max_profit=-1, best_moveX =0, best_moveY=0;
    for (int i = 0; i< fields.length ;i ++) {
      for (int j = 0; j < fields.length; j++) {
        if (possible_move[i][j].possible) {

          int pom = calculate_distance(i,j,destinationX,destinationY);
         // System.out.println( " droga: " + pom);
          profit = destination - pom;
          if (profit > max_profit){
            max_profit = profit;
            best_moveX = i;
            best_moveY = j;
          }
        }
      }
    }
    path = getPath(x,y,best_moveX,best_moveY);
    return max_profit;
  }

  public ArrayList<Integer> getPath() {
    return path;
  }
}
