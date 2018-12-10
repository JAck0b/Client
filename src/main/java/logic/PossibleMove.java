package logic;

import java.util.ArrayList;

public class PossibleMove {
  boolean possible;
  int previousX, previousY;
  //POMYSŁ : todo zamiast ścieżki po prostu skąd był poprzedni skok
  //każde pole będzie miało zapisane z jakiego pola skoczono na nie
  //będzie można wyznaczy ścieżkę cofają się
//  ArrayList pathX;
//  ArrayList pathY;
//  PossibleMove(){
//    pathX = new ArrayList<Integer>();
//    pathY = new ArrayList<Integer>();
//  }

  public void setPreviousX(int previousX) {
    this.previousX = previousX;
  }

  public void setPreviousY(int previousY) {
    this.previousY = previousY;
  }

  public int getPreviousY() {
    return previousY;
  }

  public int getPreviousX() {
    return previousX;
  }

  //  public int getPreviousX() {
//    return previousX;
//  }
//
//  public int getPreviousY() {
//    return previousY;
//  }
//
//  public ArrayList getPathX() {
//    return pathX;
//  }
//
//  public ArrayList getPathY() {
//    return pathY;
//  }
  public void setPossible(boolean possible) {
    this.possible = possible;
  }
}
