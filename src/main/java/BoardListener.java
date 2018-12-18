import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
  private Label status;
  private Label stepCounter;
  private Pane currentPlayer;
  private boolean print = true;
  private int[][] hint = new int[17][17];

  private boolean firstTime = true;


  public BoardListener(Board board, BufferedReader in, Label status, Label stepCounter, Pane currentPlayer) {
    this.fields = Board.fields;
    this.board = board;
    this.in = in;
    this.status = status;
    this.stepCounter = stepCounter;
    this.currentPlayer = currentPlayer;
  }

  @Override
  public void run() {
    String input = null;

    while (true) {
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
            board.colorCorner();
          });
//          System.out.println(input);
          setFieldsfromString(fields, input);

          if (firstTime) {
            firstTime = false;

            for (int i = 0; i < board.getMyCircles().length; i++) {
              for (int j = 0; j < board.getMyCircles()[i].length; j++) {
                if (fields[board.getMyCircles()[i][j].getX()][board.getMyCircles()[i][j].getY()] > 1) {
                  board.getMyCircles()[i][j].setStrokeColor(fields[board.getMyCircles()[i][j].getX()][board.getMyCircles()[i][j].getY()]);
                  System.out.println("Zmieniam stroke. = " + fields[board.getMyCircles()[i][j].getX()][board.getMyCircles()[i][j].getY()]);
                } else {
                  board.getMyCircles()[i][j].setStrokeColor(1);
                }
                board.getMyCircles()[i][j].setHomes();
              }
            }

          }

          for (int y = 0; y < 17; y++) {
            for (int x = 0; x < 17; x++) {
              System.out.print(board.fields[x][y] + " ");
            }
            System.out.println();
          }

        } else if (input.startsWith("PLAYER")) {
          System.out.println(input);
          int finalPlayer = Integer.parseInt(input.substring(7, 8)) - 1;
          String finalInput = "Player " + finalPlayer;
          Platform.runLater(() -> {
            status.setText(finalInput);
            switch (finalPlayer + 1) {
              case 2:
                currentPlayer.setStyle("-fx-background-color: #00008B;");
                break;
              case 3:
                currentPlayer.setStyle("-fx-background-color: #FF0000;");
                break;
              case 4:
                currentPlayer.setStyle("-fx-background-color: #006400;");
                break;
              case 5:
                currentPlayer.setStyle("-fx-background-color: #008B8B;");
                break;
              case 6:
                currentPlayer.setStyle("-fx-background-color: #4B0082;");
                break;
              case 7:
                currentPlayer.setStyle("-fx-background-color: #8B4513;");
                break;
            }
          });
        } else if (input.startsWith("STEPS")) {
          System.out.println(input);
          String finalInput1 = input;
          Platform.runLater(() -> {
            stepCounter.setText("Step = " + finalInput1.substring(6, 7));
          });

        } else if (input.startsWith("POS")) {
          input = in.readLine();
          Platform.runLater(() -> {
            board.refresh();
          });
          if (print) {
            setFieldsfromString(hint, input);
            for (int i = 0; i < board.getMyCircles().length; i++) {
              for (int j = 0; j < board.getMyCircles()[i].length; j++) {
                if (hint[board.getMyCircles()[i][j].getX()][board.getMyCircles()[i][j].getY()] == 1) {
                  int finalI = i;
                  int finalJ = j;
                  Platform.runLater(() -> {
                    board.getMyCircles()[finalI][finalJ].setStrokeWidth(2);
                    board.getMyCircles()[finalI][finalJ].setStroke(Color.GOLD);
                  });
                  System.out.println("Zmieniam stroke. = " + hint[board.getMyCircles()[i][j].getX()][board.getMyCircles()[i][j].getY()]);
                } else {
                  board.getMyCircles()[i][j].setStrokeColor(1);
                }
//                board.getMyCircles()[i][j].setHomes();
              }
            }
          }
          System.out.println(input);
        }
      } catch (IOException e) {
        System.out.println("Server is disconnected.");
        System.exit(1);
      }
    }
  }

  public void setFieldsfromString(int[][] fields, String receive) {
    int index = 0, distance;
    for (int i = 0; i < fields.length; i++) {
      for (int j = 0; j < fields.length; j++) {
        distance = 1;
        while (receive.charAt(index + distance) != ' ') {
          distance++;
        }
        fields[i][j] = Integer.parseInt(receive.substring(index, (index + distance)));
        index = index + distance + 1;
      }
    }
  }

}

//Platform.runLater(() -> {
//  fields[j][12] = 5;
//  board.refresh();
//  });