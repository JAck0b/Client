package logic;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.lang.Math.scalb;

public class Bot {
  int [][] fields;
  ArrayList<int[][]> bases;
  ArrayList<Integer> checkers_in_base;
  int id; //ID of player(nr pionków)
  int destinationX, destinationY;
  public Bot(int[][] fields){
    this.fields = fields;
    add_bases();
  }

  public void setId(int id) {
    this.id = id;

  }

  public void add_bases(){
    bases = new ArrayList<>();
    int [][] base2 = new int[][]{ //pionki z nr2 dążą do ... i symetrycznie 5 do x=y y=x
      {4,0},{4,1},{5,1},{4,2},{5,2},{5,2},{4,3},{5,3},{6,3},{7,3}
    };
    bases.add(base2);

    int [][] base3 = new int[][]{   //symetrai x=y y=x dla 3
      {12,4},{11,4},{12,5},{11,5},{10,4},{12,6},{9,4},{10,5},{11,6},{12,7}
    };
    bases.add(base3);

    int [][] base4 = new int[][]{   //symetrai x=y y=x dla 5
      {16,12},{15,12},{15,11},{14,12},{14,11},{14,10},{13,12},{13,11},{13,10},{13,9}
    };
    bases.add(base4);
  }
  public boolean checher_in_right_place(int x , int y){  //n
    //todo
    for(int i =0 ; i<checkers_in_base.size(); i=i+2){
       if(x == checkers_in_base.get(i) && y == checkers_in_base.get(i+1)){
         System.out.println("NIE ZNAJDUJE: " + x + " " + y);
         return true;
       }

    }
    return false;
  }

  /**
   * szuka pionków danego gracza
   * @return pozycje pionków gracza w liście ((x,y), (x,y), ...)
   */
  public ArrayList<Integer> find_checkers (){   //todo przerobić inne funkce na jeden tym do zwracania
    ArrayList <Integer> checkers = new ArrayList <>();
    for (int i = 0; i < fields.length; i++){
      for (int j = 0; j < fields.length; j++){
        if(fields[i][j] == id && ! checher_in_right_place(i,j)){
          checkers.add(i);
          checkers.add(j);
        }
      }
    }
    return checkers;
  }
// jak znajduje cel dkąd pionki zmierzają to nie doda pionków z bazy które jeszcze nie dotraly na pozycje końcowe
  public void find_destinaionXY() {
    int i = 0;
    boolean find = false;
    int checkX, checkY;
    while (!find){
      switch (id) {
        case 2: {
          checkX = bases.get(0)[i][0];
          checkY = bases.get(0)[i][1];
          if (fields[checkX][checkY] != id) {
            destinationX = checkX;
            destinationY = checkY;
            find = true;
          }
          else {
            checkers_in_base.add(checkX);
            checkers_in_base.add(checkY);
          }
          break;
        }
        case 3: {
          checkX = bases.get(1)[i][0];
          checkY = bases.get(1)[i][1];
          if (fields[checkX][checkY] != id) {
            destinationX = checkX;
            destinationY = checkY;
            find = true;
          }
          else {
            checkers_in_base.add(checkX);
            checkers_in_base.add(checkY);
          }
          break;
        }
        case 4: {
          checkX = bases.get(2)[i][0];
          checkY = bases.get(2)[i][1];
          if (fields[checkX][checkY] != id) {
            destinationX = checkX;
            destinationY = checkY;
            find = true;
          }
          else {
            checkers_in_base.add(checkX);
            checkers_in_base.add(checkY);
          }
          break;
        }
        case 5: {
          checkX = bases.get(2)[i][1];
          checkY = bases.get(2)[i][0];
          if (fields[checkX][checkY] != id) {
            destinationX = checkX;
            destinationY = checkY;
            find = true;
          }
          else {
            checkers_in_base.add(checkX);
            checkers_in_base.add(checkY);
          }
          break;
        }
        case 6: {
          checkX = bases.get(1)[i][1];
          checkY = bases.get(1)[i][0];
          if (fields[checkX][checkY] != id) {
            destinationX = checkX;
            destinationY = checkY;
            find = true;
          }
          else {
            checkers_in_base.add(checkX);
            checkers_in_base.add(checkY);
          }
          break;
        }
        case 7: {
          checkX = bases.get(0)[i][1];
          checkY = bases.get(0)[i][0];
          if (fields[checkX][checkY] != id) {
            destinationX = checkX;
            destinationY = checkY;
            find = true;
          }
          else {
            checkers_in_base.add(checkX);
            checkers_in_base.add(checkY);
          }
          break;
        }
      }
      i++;
      if(i == bases.get(0).length)  //przypadek nie znalezienia w wychodzenia poza tablice (nieosiągalny)
        break;
    }

  }

  public void calculate_best_move (){
    Bot_move bot_move = new Bot_move(fields);
    checkers_in_base = new ArrayList<>();
    find_destinaionXY();
    ArrayList <Integer> checkers = find_checkers(); // używa destination
    System.out.println("DESTINAION: " + destinationX + " " + destinationY  );
    bot_move.setDestinationX(destinationX);
    bot_move.setDestinationY(destinationY);

    ArrayList <Integer> path_best_move = new ArrayList<>();
    int proft = 0, max_profit = 0;
    for(int i = 0; i< checkers.size(); i = i + 2){  //po wszystkich pionkach
      //System.out.println(i);
      System.out.println("CHECKERS: " + checkers.get(i) + " " + checkers.get(i + 1) );
//      bot_move.setX(checkers.get(i));
//      bot_move.setY(checkers.get(i+1));
      proft = bot_move.calculate_best_move_of_one_checker(checkers.get(i),checkers.get(i+1));
      System.out.println("profit: " + proft);
     // System.out.println("po");
      if(proft > max_profit){
        max_profit = proft;
        path_best_move.clear();
        path_best_move = bot_move.getPath();
      }
    }
    printPath(path_best_move);
  }
  public void printPath (ArrayList <Integer> path){
    for (int i = 0; i < path.size() ; i = i + 2)
      System.out.println("(" + path.get(i) + "," + path.get(i+1) + ")");
  }


// todo wywalić pioni któe są na właściwym miejscy z chesrs
}
