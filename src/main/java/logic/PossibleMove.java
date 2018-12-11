package logic;


import java.util.ArrayList;

public class PossibleMove {
  boolean possible;
  int previousX, previousY;
  ArrayList <Integer> path;
  PossibleMove(){
    path = new ArrayList<>();
  }

  public ArrayList<Integer> getPath() {
    return path;
  }

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


  public void setPossible(boolean possible) {
    this.possible = possible;
  }
}
