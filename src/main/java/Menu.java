import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Menu {
  /**
   * Input from server.
   */
  private BufferedReader in;
  /**
   * Output from server.
   */
  private PrintWriter out;
  /**
   * Server's socket.
   */
  private Socket socket;

  @FXML
  VBox layout;

  @FXML
  TextField serverAddress;

  private int PORT = 8888;

  /**
   * This method creates new Board.
   * @throws IOException writing board.fxml
   */
  @SuppressWarnings("Duplicates")
  private void createBoard() throws IOException {
    Stage primaryStage = (Stage) layout.getScene().getWindow();
    Stage stage = new Stage();
    stage.initModality(Modality.NONE);
    stage.initOwner(primaryStage);
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("board.fxml"));
    Parent root = fxmlLoader.load();
    Board controller = fxmlLoader.getController();
    controller.setIn(in);
    controller.setOut(out);
    controller.setSocket(socket);
    stage.addEventHandler(WindowEvent.WINDOW_SHOWING, window -> controller.task());
    // When window is closed.
    stage.setOnCloseRequest(windowEvent -> {
      out.println("KILL");
      stage.close();
    });
    Scene scene = new Scene(root, 560, 750);
    stage.setResizable(false);
    stage.setScene(scene);
    stage.setTitle("Board");
    stage.show();
  }

  /**
   * This method creates New Game window.
   * @throws Exception Loading newGame.fxml
   */
  private void newGame() throws Exception {
    //TODO uruchamia okno / dialog z wyborem gier
    Stage primaryStage = (Stage) layout.getScene().getWindow();
    Stage stage = new Stage();
    stage.initOwner(primaryStage);
    stage.initModality(Modality.WINDOW_MODAL);
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newGame.fxml"));
    Parent root = fxmlLoader.load();
    NewGame newGame = fxmlLoader.getController();
    newGame.setOut(out);
    newGame.setSocket(socket);
    newGame.setServerAddress(serverAddress.getText());
    newGame.setPORT(PORT);
    stage.setOnCloseRequest(windowEvent -> out.println("KILL"));
    Scene scene = new Scene(root, 300, 400);
    stage.setScene(scene);
    stage.setTitle("New Game");
    stage.setResizable(false);
    stage.show();
  }

  @FXML
  public void playHandler() {
    try {
      String addressOfServer = (serverAddress.getText().length() == 0) ? "localhost" : serverAddress.getText();
      String input;
      socket = new Socket(addressOfServer, PORT);
      in = new BufferedReader(new InputStreamReader(
        socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
      input = in.readLine();
      if (input.equals("ADMINISTRATOR")) {
        newGame();
      } else if (input.equals("NORMAL BOARD")) {
        createBoard();
      }
    } catch (IOException e) {
      System.out.println("Cannot connect.");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Cannot create new window.");
    }
  }

  @FXML
  public void helpHandler() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Rules");
    alert.setHeaderText(null);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.setContentText("The aim is to race all one's pieces into the star corner on the opposite side of the board before opponents do the same." +
      "The destination corner is called home. Each player has 10 pieces." +
      "Each player starts with their colored pieces on one of the six points or corners of the star " +
      "and attempts to race them all home into the opposite corner." +
      "Players take turns moving a single piece, either by moving one step in any direction to an adjacent empty space, " +
      "or by jumping in one or before selected number of available consecutive hops over other single pieces. " +
      "A player may not combine hopping with a single-step move – a move consists of one or the other. " +
      "There is no capturing in Chinese Checkers, so hopped pieces remain active and in play. " +
      "Turns proceed clockwise around the board." +
      "It's possible to turn on extended hoops.");
    alert.showAndWait();
  }

  @FXML
  public void infoHandler() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Info");
    alert.setHeaderText(null);
    alert.setContentText("Copyright[2018] Jakub Pyka, Jakub Filistyński");
    alert.showAndWait();
  }

  @FXML
  public void exitHandler() {
    System.exit(0);
  }
}
