package logic;

import java.util.ArrayList;

public class CheckMove {
  public int [][] fields;
  int x,y; //pozycja obecna
  PossibleMove [][] possible_move; //gracz może się odwoływać do wszystkich poł
  // mając tą tablicę jesteśmy w stanie stwierdzić poprawność ruchu
  //unikając dużej ilości sprawdzeń

  public int getX() {
    return x;
  }
  public int getY() {
    return y;
  }

  public void setXY (int x, int y) {  //ROBI WSZYTKO TWORZY ZERUJE I OBLICZA POSSIBLE MOVE
    this.x = x;
    this.y = y;
    preapare_array_possible_move();
    calculate_possible_move();      //todo czy przesada że wszytko w jednym?
  }

  public int[][] getFields() {
    return fields;
  }

  public void setFields(int[][] fields) {
    this.fields = fields;
  }

  public void horizontal_move (int x, int y, boolean longhop, int number_of_hop){
    if(number_of_hop < 6){
      int i=1;
      boolean canhop = true;
      while(x-i>=0 && canhop){  //poszukiwanie pierwszej kuli do przeskoczenia w lewo, nie ma co sprawdzać gdy nie spełnione warunki
        if(fields [x-i][y] >= 2 && x - 2*i >= 0) { //czy kula i wychodzenie poza zakres po skoku
          //System.out.println("Kula w : " + (x-i) + " " + y);
          for(int j=1; j <= i ; j++){ //sprawdzanie czy pola za kulą są puste
            if(fields[x-i-j][y] != 1){
              canhop = false;
              break;
            }
          }
          if (canhop && !possible_move[x - 2 * i][y].possible) { //czy spełnione warunki skoku i czy pole już nie znalezione (warunek końca rekurencji)
           // System.out.println("Mozna skoczyć na : " + (x - 2 * i) + " " + y);
            possible_move [x - 2 * i][y].setPossible(true);
            possible_move [x - 2 * i][y].setPreviousX(x); //ustawianie wartości z której można dotrzeć na dane pole
            possible_move [x - 2 * i][y].setPreviousY(y);
            hop(x - 2 *i,y,longhop,number_of_hop+1);
          }
          break; //po znalezieniu pierwszej nie może szukać dalej (nawet jeśli if był nie było możliwego skoku)
        }else if (fields [x-i][y] ==0) //poza planszą
          break;
        i++;
      }
      //analogicznie w prawo
      i=1;
      canhop = true;
      while(x+i<fields.length && canhop){  //poszukiwanie pierwszej kuli do przeskoczenia w prawo, nie ma co sprawdzać gdy nie spełnione warunki
        if(fields [x+i][y] >= 2 && x + 2*i < fields.length) { //czy kula i wychodzenie poza zakres po skoku
          //System.out.println("Kula w : " + (x+i) + " " + y);
          for(int j=1; j <= i ; j++){ //sprawdzanie czy pola za kulą są puste
            if(fields[x+i+j][y] != 1){
              canhop = false;
              break;
            }
          }
          if (canhop && !possible_move[x + 2 * i][y].possible) { //czy spełnione warunki skoku i czy pole już nie znalezione (warunek końca rekurencji)
          //  System.out.println("Mozna skoczyć na : " + (x + 2 * i) + " " + y);
            possible_move[x + 2 * i][y].setPossible(true);
            possible_move [x + 2 * i][y].setPreviousX(x); //ustawianie wartości z której można dotrzeć na dane pole
            possible_move [x + 2 * i][y].setPreviousY(y);
            hop(x + 2 *i,y ,longhop,number_of_hop+1);
          }
          break; //po znalezieniu pierwszej nie może szukać dalej (nawet jeśli if był nie było możliwego skoku)
        }else if (fields [x+i][y] ==0) //poza planszą
          break;
        i++;
      }
    }
  }
  public void vertical_move (int x, int y, boolean longhop, int number_of_hop){
    if(number_of_hop < 6){
      int i=1;
      boolean canhop = true;
      while(y-i>=0 && canhop){  //poszukiwanie pierwszej kuli do przeskoczenia w górę, nie ma co sprawdzać gdy nie spełnione warunki
        if(fields [x][y-i] >= 2 && y - 2*i >= 0) { //czy kula i wychodzenie poza zakres po skoku
          // System.out.println("Kula w : " + x + " " + (y - i) );
          for(int j=1; j <= i ; j++){ //sprawdzanie czy pola za kulą są puste
            if(fields[x][y-i-j] != 1){
              canhop = false;
              break;
            }
          }
          if (canhop && !possible_move[x][y - 2 * i].possible) { //czy spełnione warunki skoku i czy pole już nie znalezione (warunek końca rekurencji)
          //  System.out.println("Mozna skoczyć na : " + x + " " + (y - 2 * i));
            possible_move[x][y - 2 * i].setPossible(true);
            possible_move [x][y - 2 * i].setPreviousX(x); //ustawianie wartości z której można dotrzeć na dane pole
            possible_move [x][y - 2 * i].setPreviousY(y);
            hop(x,y - 2 *i,longhop,number_of_hop+1);
          }
          break; //po znalezieniu pierwszej nie może szukać dalej (nawet jeśli if był nie było możliwego skoku)
        }else if (fields [x][y-i] ==0) //poza planszą
          break;
        i++;
      }
      //analogicznie w dół
      i=1;
      canhop = true;
      while(y+i<fields.length && canhop){  //poszukiwanie pierwszej kuli do przeskoczenia w dół, nie ma co sprawdzać gdy nie spełnione warunki
        if(fields [x][y+i] >= 2 && y + 2*i < fields.length) { //czy kula i wychodzenie poza zakres po skoku
          // System.out.println("Kula w : " + x + " " + (y + i));
          for(int j=1; j <= i ; j++){ //sprawdzanie czy pola za kulą są puste
            if(fields[x][y+i+j] != 1){
              canhop = false;
              break;
            }
          }
          if (canhop && !possible_move[x][y + 2 * i].possible) {//czy spełnione warunki skoku i czy pole już nie znalezione (warunek końca rekurencji)
            System.out.println("Mozna skoczyć na : " + x + " " + (y  + 2 *i));
            possible_move[x][y + 2 * i].setPossible(true);
            possible_move [x][y + 2 * i].setPreviousX(x); //ustawianie wartości z której można dotrzeć na dane pole
            possible_move [x][y + 2 * i].setPreviousY(y);
            hop(x,y + 2 *i,longhop,number_of_hop+1);
          }
          break; //po znalezieniu pierwszej nie może szukać dalej (nawet jeśli if był nie było możliwego skoku)
        } else if (fields [x][y+i] ==0) //poza planszą
          break;
        i++;
      }
    }
  }
  public void cross_move (int x, int y, boolean longhop, int number_of_hop){
    if(number_of_hop < 6){
      int i=1;
      boolean canhop = true;
      while(x-i>=0 && y-i>=0 && canhop){  //poszukiwanie pierwszej kuli do przeskoczenia w góra-lewo, nie ma co sprawdzać gdy nie spełnione warunki
        if(fields [x-i][y-i] >= 2 && y - 2*i >= 0 && x - 2*i >= 0) { //czy kula i wychodzenie poza zakres po skoku
          //System.out.println("Kula w skos: " + (x-i) + " " + (y - i) + " JEST TAM = " + fields [x-i][y-i]);
          for(int j=1; j <= i ; j++){ //sprawdzanie czy pola za kulą są puste
            if(fields[x-i-j][y-i-j] != 1){
              canhop = false;
              break;
            }
          }
          if (canhop && !possible_move[x - 2 * i][y - 2 * i].possible) {//czy spełnione warunki skoku i czy pole już nie znalezione (warunek końca rekurencji)
            //System.out.println("Mozna skoczyć na : " + (x - 2 *i)  + " " + (y - 2 *i) );
            possible_move[x - 2 *i][y - 2 * i].setPossible(true);
            possible_move [x - 2 * i][y - 2 * i].setPreviousX(x); //ustawianie wartości z której można dotrzeć na dane pole
            possible_move [x - 2 * i][y - 2 * i].setPreviousY(y);
            hop(x - 2 *i,y - 2 *i,longhop,number_of_hop+1);
          }
          break; //po znalezieniu pierwszej nie może szukać dalej (nawet jeśli if był nie było możliwego skoku)
        }else if (fields [x-i][y-i] ==0) //poza planszą
          break;
        i++;
      }
      //analogicznie w dół-prawo
      i=1;
      canhop = true;
      while(x+i<fields.length && y+i < fields.length && canhop){  //poszukiwanie pierwszej kuli do przeskoczenia w górę, nie ma co sprawdzać gdy nie spełnione warunki
        if(fields [x+i][y+i] >= 2 && x + 2*i < fields.length && y + 2*i < fields.length) { //czy kula i wychodzenie poza zakres po skoku
          // System.out.println("Kula w skos: " + (x + i) + " " + (y + i) + " JEST TAM = " + fields [x+i][y+i]);
          for(int j=1; j <= i ; j++){ //sprawdzanie czy pola za kulą są puste
            if(fields[x+i+j][y+i+j] != 1){
              canhop = false;
              break;
            }
          }
          if (canhop && !possible_move[x + 2 * i][y + 2 * i].possible)  {//czy spełnione warunki skoku i czy pole już nie znalezione (warunek końca rekurencji)
           // System.out.println("Mozna skoczyć na : " + (x + 2 *i) + " " + (y  + 2 *i));
            possible_move[x + 2 *i][y + 2 * i].setPossible(true);
            possible_move [x + 2 * i][y + 2 * i].setPreviousX(x); //ustawianie wartości z której można dotrzeć na dane pole
            possible_move [x + 2 * i][y + 2 * i].setPreviousY(y);
            hop(x + 2 *i,y + 2 *i,longhop,number_of_hop+1);
          }
          break; //po znalezieniu pierwszej nie może szukać dalej (nawet jeśli if był nie było możliwego skoku)
        } else if (fields [x+i][y+i] ==0) //poza planszą
          break;
        i++;
      }
    }
  }

  public void preapare_array_possible_move (){
    int size = fields.length;
    possible_move = new PossibleMove[size][size];
    for (int i = 0; i< fields.length ;i ++) {
      for (int j = 0; j < fields.length; j++) {
        possible_move[i][j] = new PossibleMove();
        possible_move[i][j].setPossible(false);
      }
    }
  }
  public void hop (int x, int y, boolean longhop, int number_of_hop){
    //System.out.println("Moja pozycja : " + x + " " + y);
    horizontal_move(x,y,longhop, number_of_hop);
    vertical_move(x,y,longhop, number_of_hop);
    cross_move(x,y,longhop, number_of_hop );
  }
  public void calculate_possible_move (){//potrzebne rozbicie bo po skoku nie ma możliwości ruchu o jeden
    int help = fields[x][y];  //nie może skakać w rekurencji przez swój pionke
    fields[x][y]=1;


    if(x-1 >= 0 && fields [x-1][y] == 1){
      possible_move [x-1][y].setPossible(true);
//      possible_move [x-1][y].path.add(x);
//      possible_move [x-1][y].path.add(y);
      possible_move [x-1][y].setPreviousX(x);
      possible_move [x-1][y].setPreviousY(y);
    }
    if(x+1 < fields.length && fields [x+1][y] == 1){
      possible_move [x+1][y].setPossible(true);
//      possible_move [x+1][y].path.add(x);
//      possible_move [x+1][y].path.add(y);
      possible_move [x+1][y].setPreviousX(x);
      possible_move [x+1][y].setPreviousY(y);
    }
    //System.out.println("Pole " + fields [x][y-1]);
    if(y-1 >= 0 && fields [x][y-1] == 1){
      //System.out.println("tutaj :" + x+ " " + y);
      possible_move [x][y-1].setPossible(true);
//      possible_move [x][y-1].path.add(x);
//      possible_move [x][y-1].path.add(y);
      possible_move [x][y-1].setPreviousX(x);
      possible_move [x][y-1].setPreviousY(y);
    }
    if(y+1 < fields.length && fields [x][y+1] == 1){
      possible_move [x][y+1].setPossible(true);
      possible_move [x][y+1].setPreviousX(x);
      possible_move [x][y+1].setPreviousY(y);
    }
    if(x-1 >= 0 && y-1 >= 0 && fields [x-1][y-1] == 1){
      possible_move [x-1][y-1].setPossible(true);
      possible_move [x-1][y-1].setPreviousX(x);
      possible_move [x-1][y-1].setPreviousY(y);
    }
    if(x+1 < fields.length && y+1 < fields.length && fields [x+1][y+1] == 1){
      possible_move [x+1][y+1].setPossible(true);
      possible_move [x+1][y+1].setPreviousX(x);
      possible_move [x+1][y+1].setPreviousY(y);
    }

    possible_move[x][y].setPossible(true);//żeby rekurencja się nie cofała, PRZYPADEK MUSI BYĆ WYKLUCZONY PÓŹNIEJ
    hop(x,y,true,0);

    fields[x][y]=help;
  }
  /**
   * @return array of possiblie move for current ball
   * 1 : possible move
   * 0 : not possible move
   */
  public PossibleMove [][] getMove_option() {
    return possible_move;
  }
  public boolean check_move (int check_x, int check_y){
    if (possible_move [check_x][check_y].possible && !( x == check_x && y == check_y ))
      return true;
    else
      return false;
  }
  public void printArray() {    //printujemy odwrotnie !!!!!!
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
   *
   * @param check_x współrzędna x do której szukamy ściżki
   * @param check_y współrzędna y do krórej szukamy ścieżki
   * @return lista postaci ((x,y),(x,y) ...)
   * gdzie pierwsze wystapienie to szukana pozycja
   * a ostatnia to obecna pozycja
   * MOŻE ZWRÓCIĆ PUSTĄ SCIEŻKĘ GDY NIE ISTNIEJE POŁĄCZENIE
   */
  public ArrayList getPath(int check_x, int check_y) {
    ArrayList<Integer> path = new ArrayList<>();
    if(check_move(check_x,check_y)){
      path.add(check_x);
      path.add(check_y);
      int help_x, help_y;
      while(check_x != getX() && check_y!= getY()){
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
  public void printPath (ArrayList <Integer> path){
    for (int i = 0; i < path.size() ; i = i + 2)
      System.out.println("(" + path.get(i) + "," + path.get(i+1) + ")");
  }


}
