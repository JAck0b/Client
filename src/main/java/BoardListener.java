import javafx.application.Platform;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
   * Status label on Board.
   */
  private Label status;
  /**
   * Steps counter label on Board.
   */
  private Label stepCounter;
  /**
   * Pane with color of current player.
   */
  private Pane currentPlayer;
  /**
   * CheckBox with hints.
   */
  private CheckBox hints;
  /**
   * Pane with player color.
   */
  private Pane playersColor;
  /**
   * Table with all possible moves.
   */
  private int[][] hintTable = new int[17][17];
  /**
   * Indicate initialization state.
   */
  private boolean firstTime = true;

  BoardListener(Board board, BufferedReader in, Label status, Label stepCounter, Pane currentPlayer, CheckBox hints, Pane playersColor) {
    this.fields = board.getFields();
    this.board = board;
    this.in = in;
    this.status = status;
    this.stepCounter = stepCounter;
    this.currentPlayer = currentPlayer;
    this.hints = hints;
    this.playersColor = playersColor;
  }


  /**
   * Method with listener which changes board.
   */
  @Override
  public void run() {
    String input;

    while (true) {
      try {
        input = in.readLine();
        if (input.startsWith("BOARD")) {
          input = in.readLine();
          String finalInput = input;
          Platform.runLater(() -> {
            setFieldsfromString(fields, finalInput);
            board.refresh();
          });
          setFieldsfromString(fields, input);

          if (firstTime) {
            firstTime = false;
            for (int i = 0; i < board.getMyCircles().length; i++) {
              for (int j = 0; j < board.getMyCircles()[i].length; j++) {
                if (fields[board.getMyCircles()[i][j].getX()][board.getMyCircles()[i][j].getY()] > 1) {
                  int finalI = i;
                  int finalJ = j;
                  Platform.runLater(() -> board.getMyCircles()[finalI][finalJ].setStrokeColor(fields[board.getMyCircles()[finalI][finalJ].getX()][board.getMyCircles()[finalI][finalJ].getY()]));
                } else {
                  int finalI1 = i;
                  int finalJ1 = j;
                  Platform.runLater(() -> board.getMyCircles()[finalI1][finalJ1].setStrokeColor(1));
                }
                int finalI2 = i;
                int finalJ2 = j;
                Platform.runLater(() -> board.getMyCircles()[finalI2][finalJ2].setHomes());
              }
            }
          }

        } else if (input.startsWith("PLAYER")) {
          int finalPlayer = Integer.parseInt(input.substring(7, 8)) + 1;
          int nextPlayer = Integer.parseInt(input.substring(9, 10));
          String finalInput = "Player " + finalPlayer;

          Platform.runLater(() -> {
            status.setText(finalInput);
            setAppropriateColor(nextPlayer, currentPlayer);
          });

        } else if (input.startsWith("STEPS")) {
          String[] finalInput1 = input.split(" ");

          Platform.runLater(() -> stepCounter.setText("Step = " + finalInput1[1]));

        } else if (input.startsWith("POS")) {
          input = in.readLine();

          Platform.runLater(() -> {
            board.refresh();
            for (int i = 0; i < board.getMyCircles().length; i++) {
              for (int j = 0; j < board.getMyCircles()[i].length; j++) {
                if (board.getMyCircles()[i][j].isActive()) {
                  board.getMyCircles()[i][j].setStroke(Color.GOLD);
                  board.getMyCircles()[i][j].setStrokeWidth(5);

                }
              }
            }
          });

          if (hints.isSelected()) {
            setFieldsfromString(hintTable, input);
            for (int i = 0; i < board.getMyCircles().length; i++) {
              for (int j = 0; j < board.getMyCircles()[i].length; j++) {
                if (hintTable[board.getMyCircles()[i][j].getX()][board.getMyCircles()[i][j].getY()] == 1) {
                  int finalI = i;
                  int finalJ = j;

                  Platform.runLater(() -> {
                    board.getMyCircles()[finalI][finalJ].setStrokeWidth(5);
                    board.getMyCircles()[finalI][finalJ].setStroke(Color.GOLD);
                  });

                }
              }
            }
          }
        } else if (input.startsWith("YOURID")) {
          int finalId = Integer.parseInt(input.substring(7, 8));

          Platform.runLater(() -> setAppropriateColor(finalId, playersColor));

        } else if (input.startsWith("KILL")) {
          break;
        }
      } catch (IOException e) {
        System.out.println("Server is disconnected.");
        System.exit(1);
      }
    }

    Platform.runLater(() -> {
      try {
        board.getSocket().close();
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Cannot close window.");
      }
      ((Stage) (board.layout.getScene().getWindow())).close();
    });

  }

  /**
   * This method set appropriate color to Pane.
   * @param finalInt Number of color.
   * @param pane  Changed pane.
   */
  private void setAppropriateColor(int finalInt, Pane pane) {
    switch (finalInt) {
      case 2:
        pane.setStyle("-fx-background-color: #00008B;");
        break;
      case 3:
        pane.setStyle("-fx-background-color: #DC143C;");
        break;
      case 4:
        pane.setStyle("-fx-background-color: #006400;");
        break;
      case 5:
        pane.setStyle("-fx-background-color: #008B8B;");
        break;
      case 6:
        pane.setStyle("-fx-background-color: #4B0082;");
        break;
      case 7:
        pane.setStyle("-fx-background-color: #8B4513;");
        break;
    }
  }

  /**
   * This method convert string into table of ints.
   * @param fields Table of ints.
   * @param receive Received string.
   */
  private void setFieldsfromString(int[][] fields, String receive) {
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
