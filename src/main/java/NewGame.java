import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class NewGame {
  private PrintWriter out;
  private BufferedReader in;
  private Socket socket;
  private String serverAddress;
  private int PORT;

  @FXML
  VBox layout;
  @FXML
  Slider player;
  @FXML
  Slider boot;
  @FXML
  CheckBox checkBox;

  @FXML
  public void initialize() {
    player.valueProperty().addListener(
      (observable, oldvalue, newvalue) ->
      {
        int i = newvalue.intValue();
        boot.setMax(6 - i);
        boot.setMin(i % 2);
        boot.setValue(0);
        boot.setMajorTickUnit(2);
      });
  }

  @FXML
  public void playHandler() {
    out.println("NEW " + (int) player.getValue() + " " + (int) boot.getValue() + " " + ((checkBox.isSelected()) ? 1 : 0));
    try {
      connectToGame();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void createBoard() throws IOException {
    Stage primaryStage = (Stage) layout.getScene().getWindow();
    Stage stage = new Stage();
    stage.initOwner(primaryStage.getOwner());
    stage.initModality(Modality.NONE);
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("board.fxml"));
    Parent root = fxmlLoader.load();
    Board controller = fxmlLoader.getController();
    controller.setOut(out);
    controller.setIn(in);
    controller.setSocket(socket);
    stage.addEventHandler(WindowEvent.WINDOW_SHOWING, window -> controller.task());
    // When window is closed.
    stage.setOnCloseRequest(windowEvent -> {
      out.println("KILL");
      System.out.println("KILL w normal board.");
      stage.close();
    });
    Scene scene = new Scene(root, 560, 750);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.setTitle("Board");
    stage.show();
    Stage secondStage = (Stage) layout.getScene().getWindow();
    secondStage.close();
  }

  private void connectToGame() throws IOException {
    System.out.println(serverAddress);
    System.out.println(PORT);
    socket.close();
    socket = new Socket(serverAddress, PORT);
    in = new BufferedReader(new InputStreamReader(
      socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);
    String input = in.readLine();
    System.out.println(input);
    if (input.equals("NORMAL BOARD"))
      createBoard();
  }


  void setOut(PrintWriter out) {
    this.out = out;
  }

  void setSocket(Socket socket) {
    this.socket = socket;
  }

  void setServerAddress(String serverAddress) {
    this.serverAddress = serverAddress;
  }

  void setPORT(int PORT) {
    this.PORT = PORT;
  }
}
