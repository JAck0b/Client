import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;

public class BoardListener extends Thread {

  /**
   * Representation of board.
   */
  private int[][] fields;

  /**
   * Bord representation.
   */
  private Board board;

  /**
   * Input from server.
   */
  private BufferedReader in;



  public BoardListener(Board board, BufferedReader in) {
    this.fields = Board.fields;
    this.board = board;
    this.in = in;
  }

  @Override
  public void run() {
    String input = null;
    while(true) {
      try {
        while(input == null) {
          input = in.readLine();
          System.out.println(input);
        }
        if (input.startsWith("BOARD")) {
          System.out.println(input);
          input = in.readLine();
          String tmp = input;
          Platform.runLater(() -> {
            setFieldsfromString(fields, tmp);
            board.refresh();
          });
          System.out.println(input);
          for (int y = 0; y < 17; y++) {
            for (int x = 0; x < 17; x++) {
              System.out.print(fields[x][y]);
            }
            System.out.println();
          }
        } else if (input.startsWith("PLAYER")) {
          System.out.println(input);
        } else if (input.startsWith("STEPS")) {
          System.out.println(input);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void setFieldsfromString(int[][] fields,String receive) {
    int index=0, distance;
    for (int i = 0; i < fields.length; i++){
      for (int j = 0 ; j < fields.length ; j++){
        distance = 1 ;
        while(receive.charAt(index + distance) != ' '){
          distance ++;
        }
        fields [i][j] = Integer.parseInt(receive.substring(index,(index + distance)));
        index = index + distance + 1;
      }
    }
  }

}

//Platform.runLater(() -> {
//  fields[j][12] = 5;
//  board.refresh();
//  });