import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Menu {

  private BufferedReader in;
  private PrintWriter out;
  private Socket socket;

  @FXML
  VBox layout;

  @FXML
  TextField serverAddress;

  private int PORT = 8888;

  @FXML
  public void initialize() {
  }
  // TODO Zmienć modalność po testach
  private void createBoard() throws IOException {
    Stage primaryStage = (Stage) layout.getScene().getWindow();
    Stage stage = new Stage();
    stage.initOwner(primaryStage);
//    stage.initModality(Modality.WINDOW_MODAL);
    stage.initModality(Modality.NONE);
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("board.fxml"));
    Parent root = fxmlLoader.load();
    Board controller = fxmlLoader.<Board>getController();
    controller.setOut(out);
    controller.setIn(in);
    out.println("Ready");
    Scene scene = new Scene(root, 560, 750);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.setTitle("Board");
    stage.show();
  }

  private void newGame(PrintWriter out, Socket socket) throws Exception {
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
    Scene scene = new Scene(root, 500, 600);
    stage.setScene(scene);
    stage.setTitle("Games");
    stage.setResizable(false);
    stage.show();
  }

  @FXML
  public void playHandler() {
    try {
      String addressOfServer = (serverAddress.getText().length() == 0) ? "localhost" : serverAddress.getText();
      String input = null;
      socket = new Socket(addressOfServer, PORT);
      in = new BufferedReader(new InputStreamReader(
        socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
      input = in.readLine();
      System.out.println(input);
      if (input.equals("ADMINISTRATOR")) {
        newGame(out, socket);
      } else if (input.equals("NORMAL BOARD")) {
        createBoard();
      }
    } catch (IOException e) {
//      e.printStackTrace();
      System.out.println("Cannot connect.");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Cannot create new window.");
    }
//    newGame.setDisable(false);
  }

  @FXML
  public void exitHandler(ActionEvent e) {
    System.exit(0);
  }

  @FXML
  public void helpHandler(ActionEvent e) throws IOException {
    Stage primaryStage = (Stage) layout.getScene().getWindow();
    Stage stage = new Stage();
    stage.initOwner(primaryStage);
    stage.initModality(Modality.WINDOW_MODAL);
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("board.fxml"));
    Parent root = fxmlLoader.load();
    Board controller = fxmlLoader.<Board>getController();
    Scene scene = new Scene(root, 560, 750);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.setTitle("Board");
    stage.show();
  }
}
