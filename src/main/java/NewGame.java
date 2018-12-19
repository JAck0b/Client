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

  /**
   * Output into server.
   */
  private PrintWriter out;
  /**
   * Input from server.
   */
  private BufferedReader in;
  /**
   * Server's socket.
   */
  private Socket socket;
  /**
   * Server's ip address.
   */
  private String serverAddress;
  /**
   * Server's port.
   */
  private int PORT;

  @FXML
  VBox layout;
  @FXML
  Slider player;
  @FXML
  Slider bot;
  @FXML
  CheckBox checkBox;
  @FXML
  Slider hoops;

  @FXML
  public void initialize() {
    player.valueProperty().addListener(
      (observable, oldvalue, newvalue) ->
      {
        int i = newvalue.intValue();
        bot.setMax(6 - i);
        bot.setMin(i % 2);
        bot.setValue(0);
        bot.setMajorTickUnit(2);
      });
  }

  @FXML
  public void playHandler() {
    out.println("NEW " + (int) player.getValue() + " " + (int) bot.getValue() + " " + ((checkBox.isSelected()) ? 1 : 0)
    + " " + (int)hoops.getValue());
    try {
      connectToGame();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method creates new Board.
   * @throws IOException writing board.fxml
   */
  @SuppressWarnings("Duplicates")
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

  /**
   * This method runs after initialization of game and automatically connect with server to game.
   * @throws IOException Close socket exception
   */
  private void connectToGame() throws IOException {
    socket.close();
    socket = new Socket(serverAddress, PORT);
    in = new BufferedReader(new InputStreamReader(
      socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);
    String input = in.readLine();
    if (input.equals("NORMAL BOARD"))
      createBoard();
  }


  void setOut(PrintWriter out) {
    this.out = out;
  }

  PrintWriter getOut() {
    return this.out;
  }

  void setSocket(Socket socket) {
    this.socket = socket;
  }

  Socket getSocket() {
    return this.socket;
  }

  void setServerAddress(String serverAddress) {
    this.serverAddress = serverAddress;
  }

  String getServerAddress() {
    return this.serverAddress;
  }

  void setPORT(int PORT) {
    this.PORT = PORT;
  }

  int getPORT() {
    return this.PORT;
  }
}
