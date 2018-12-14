package logic;

import java.util.ArrayList;

public class Bot {
  private int [][] fields;
  private ArrayList<int[][]> bases;
  private ArrayList<Integer> checkers_in_base;
  private ArrayList <Integer> path_best_move = new ArrayList<>();
  private int id; //ID of player(nr pionków)
  private int destinationX, destinationY;
  private boolean bot_skip_move, longhop;
  private int steps_in_game;

  public Bot(int[][] fields, boolean longhop){
    this.fields = fields;
    this.longhop = longhop;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setSteps_in_game(int steps_in_game) {
    this.steps_in_game = steps_in_game;
  }

  public void setBases(ArrayList<int[][]> bases) {
    this.bases = bases;
  }

  public ArrayList<Integer> getPath_best_move() {
    return path_best_move;
  }


  private boolean checher_in_right_place(int x, int y){  //n
    for(int i =0 ; i<checkers_in_base.size(); i=i+2){
       if(x == checkers_in_base.get(i) && y == checkers_in_base.get(i+1)){
         return true;
       }

    }
    return false;
  }
  /**
   * szuka pionków danego gracza
   * @return pozycje pionków gracza w liście ((x,y), (x,y), ...)
   */
  private ArrayList<Integer> find_checkers(){
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
  private void find_destinaionXY() {
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
    Bot_move bot_move = new Bot_move(fields,longhop,steps_in_game,bases);
    checkers_in_base = new ArrayList<>();
    find_destinaionXY();
    ArrayList <Integer> checkers = find_checkers();
    bot_move.setDestinationX(destinationX);
    bot_move.setDestinationY(destinationY);


    int proft, max_profit = -10;
    bot_skip_move = true;
    for(int i = 0; i< checkers.size(); i = i + 2){  //po wszystkich pionkach
      proft = bot_move.calculate_best_move_of_one_checker(checkers.get(i),checkers.get(i+1));
      if(bot_move.move_found){
        bot_skip_move = false;
      }
      if(proft > max_profit){
        max_profit = proft;
        path_best_move.clear();
        path_best_move = bot_move.getPath();
      }
    }
  }

  public boolean isBot_skip_move() {
    return bot_skip_move;
  }

  public void printPath (ArrayList <Integer> path){
    for (int i = 0; i < path.size() ; i = i + 2)
      System.out.println("(" + path.get(i) + "," + path.get(i+1) + ")");
  }
}
