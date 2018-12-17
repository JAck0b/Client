import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

  /**
   * Status text field.
   */
  private TextField status;



  public BoardListener(Board board, BufferedReader in, TextField status) {
    this.fields = Board.fields;
    this.board = board;
    this.in = in;
    this.status = status;
  }

  @Override
  public void run() {
    String input = null;
    if (in == null) {
      System.out.println("Null");
    }
    while(true) {
      try {
//        while(input == null) {
          input = in.readLine();
//          System.out.println("Przed");
          System.out.println("Komenda = " + input);
//          System.out.println("PO1");
//        }
        if (input.startsWith("BOARD")) {
          System.out.println(input);
          input = in.readLine();
//          System.out.println("PO2");
          String finalInput = input;
          Platform.runLater(() -> {
            setFieldsfromString(fields, finalInput);
            board.refresh();
          });
//          System.out.println(input);
          setFieldsfromString(fields, input);
          for (int y = 0; y < 17; y++) {
            for (int x = 0; x < 17; x++) {
              System.out.print(board.fields[x][y] + " ");
            }
            System.out.println();
          }

        } else if (input.startsWith("PLAYER")) {
          System.out.println(input);

          String finalInput = input;
          Platform.runLater(() -> {
            status.setText(finalInput);
          });
        } else if (input.startsWith("STEPS")) {
          System.out.println(input);

        }
      } catch (IOException e) {
        System.out.println("Server is disconnected.");
        System.exit(1);
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