package logic;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class Bot_move {
  private int [][] fields;
  private int destinationX, destinationY;
  private ArrayList<int[][]> bases;
  private ArrayList path;
  private PossibleMove [][] possible_move;
  boolean move_found = true;
  private CheckMove checkMove;
  private int steps_in_game;
  Bot_move(int [][] fields, boolean longhop, int steps_in_game, ArrayList<int [][]> bases ){
    this.fields = fields;
    this.steps_in_game = steps_in_game;
    this.bases = bases;
    checkMove = new CheckMove(longhop);

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
  private int calculate_distance(int x, int y, int destinationX, int destinationY){
    int skos =min (abs(x-destinationX), abs(y - destinationY));
    if( (x-destinationX >0 && y - destinationY >0 ) || (x-destinationX <0 && y - destinationY <0 ))//skosy
      return abs(x-destinationX) + abs(y - destinationY) - skos;
    return abs(x-destinationX) + abs(y - destinationY);
  }

  void setDestinationX(int destinationX) {
    this.destinationX = destinationX;
  }

  void setDestinationY(int destinationY) {
    this.destinationY = destinationY;
  }
  private boolean still_in_base(int x, int y){
    int id = fields [x][y];
    for (int i =0; i< 10; i ++){
      switch (id) {
        case 5: {
          if (x == bases.get(0)[i][0] && y == bases.get(0)[i][1])
            return true;
          break;
        }
        case 6: {
          if( x == bases.get(1)[i][0] && y== bases.get(1)[i][1])
            return true;
          break;
        }
        case 7: {
          if( x == bases.get(2)[i][0] && y == bases.get(2)[i][1])
            return true;
          break;
        }
        case 2: {
          if( x == bases.get(2)[i][1] && y == bases.get(2)[i][0] )
            return true;
          break;
        }
        case 3: {
          if( x == bases.get(1)[i][1]  && y == bases.get(1)[i][0] )
            return true;
          break;
        }
        case 4: {
          if( x == bases.get(0)[i][1] && y == bases.get(0)[i][0] )
            return true;
          break;
        }
      }
    }
    return false;
  }

  /**
   * todo warunek czy istnieje ścieżka chyba nie potrzebny
   * @param x początkowa pozycja
   * @param y początkowa pozycja
   * @param check_x współrzędna x do której szukamy ściżki
   * @param check_y współrzędna y do krórej szukamy ścieżki
   * @return zwraca ścieżkę w postaci listay: ((x,y),(x,y) ...)
   *   gdzie pierwsze wystapienie to szukana pozycja
   *   a ostatnia to obecna pozycja
   */
  private ArrayList getPath(int x, int y, int check_x, int check_y) { // zwraca ścieżkę mie
    ArrayList<Integer> path = new ArrayList<>();
    int help_x, help_y;
    if (possible_move [check_x][check_y].possible && !( x == check_x && y == check_y )){
      path.add(check_x);
      path.add(check_y);

      while( !( x == check_x && y == check_y )){
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
  int calculate_best_move_of_one_checker(int x, int y){ //wejście wspórzędne pkt z któego się ruszamy
    checkMove.setFields(fields);
    checkMove.setXY(x,y); //ROBI WSZYTKO TWORZY ZERUJE I OBLICZA POSSIBLE MOVE
    possible_move = checkMove.getPossible_move();
    int destination = calculate_distance(x,y,destinationX,destinationY);

    // PRZYMUSOWE WYJSCIE Z BAZY
    if(steps_in_game > 7 && still_in_base(x,y)){
//      System.out.println("WYCHODZE Z BAZY");
      destination = destination + 2;
    }
    else if(steps_in_game > 10 && still_in_base(x,y)){
//      System.out.println("WYCHODZE Z BAZY");
      destination = destination + 4;
    }
    else if(steps_in_game > 15 && still_in_base(x,y)){
//      System.out.println("WYCHODZE Z BAZY");
      destination = destination + 6;
    }
    else if(steps_in_game > 20 && still_in_base(x,y)){
//      System.out.println("WYCHODZE Z BAZY");
      destination = destination + 8;
    }
    
    boolean anypossible = false;  //zmienna do skipowania ruchu gdy nie ma możliwości ruchu
    int profit=0, max_profit=-10, best_moveX =0, best_moveY=0;
    for (int i = 0; i< fields.length ;i ++) {
      for (int j = 0; j < fields.length; j++) {
        if (possible_move[i][j].possible && !(i == x && j == y)) {//sprawdza możliwości ruchu (nie uwzglęgniamy pola na którym się znajduje)
          anypossible = true;
          int pom = calculate_distance(i,j,destinationX,destinationY);  // oblicza odległość po skoku
          profit = destination - pom;
          if (profit > max_profit){
            max_profit = profit;
            best_moveX = i;
            best_moveY = j;
          }
        }
      }
    }
    if(anypossible){
      path = getPath(x,y,best_moveX,best_moveY);
      move_found = true;
    }

    else{
      move_found = false;
    }
    return max_profit;
  }

  ArrayList<Integer> getPath() {
    return path;
  }
}
