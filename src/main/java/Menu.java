import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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

  private BufferedReader in;
  private PrintWriter out;
  private Socket socket;

  @FXML
  VBox layout;

  @FXML
  TextField serverAddress;

  private int PORT = 8888;


  // TODO Zmienć modalność po testach
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
    stage.setOnCloseRequest( windowEvent ->  {
      out.println("KILL");
      System.out.println("KILL");
      stage.close();
    });
    Scene scene = new Scene(root, 560, 750);
    stage.setResizable(false);
    stage.setScene(scene);
    stage.setTitle("Board");
    stage.show();
  }

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
    stage.setOnCloseRequest( windowEvent ->  {
      out.println("KILL");
      System.out.println("KILL");
    });
    Scene scene = new Scene(root, 300, 330);
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
      System.out.println(input);
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

  }

  @FXML
  public void settingHandler() {
  }

  @FXML
  public void exitHandler() {
    System.exit(0);
  }
}
