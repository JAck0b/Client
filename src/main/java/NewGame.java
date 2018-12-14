import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class NewGame {
  // TODO Zmienić ewentualnie na ComboBox
  private PrintWriter out;
  private BufferedReader in;
  private Socket socket;
  private String serverAddress;
  private  int PORT;

  @FXML
  VBox layout;
  @FXML
  public void oneOneHandler() throws IOException{
    out.println("NEW 1 1");
    closeStage();
    connectToGame();
  }
  @FXML
  public void oneThreeHandler() throws IOException {
    out.println("NEW 1 3");
    closeStage();
    connectToGame();
  }
  @FXML
  public void twoHandler() throws IOException {
    out.println("NEW 2 0");
    closeStage();
    connectToGame();
  }
  @FXML
  public void twoFourHandler() {
    out.println("NEW 2 4");
    closeStage();

  }
  @FXML
  public void threeThreeHandler() {
    out.println("NEW 3 3");
    closeStage();

  }
  @FXML
  public void sixHandler() {
    out.println("NEW 6 0");
    closeStage();
  }

  private void createBoard() throws IOException {
    Stage primaryStage = (Stage) layout.getScene().getWindow();
    Stage stage = new Stage();
    stage.initOwner(primaryStage.getOwner());
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
    Stage secondStage = (Stage) layout.getScene().getWindow();
    secondStage.close();
  }

  private void connectToGame() throws IOException {
    System.out.println(serverAddress);
    System.out.println(PORT);
    socket = new Socket(serverAddress, PORT);
    in = new BufferedReader(new InputStreamReader(
      socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);
    String input = in.readLine();
    System.out.println(input);
    if (input.equals("NORMAL BOARD"))
    createBoard();
  }

  private void closeStage() {
    try {
      socket.close();
      System.out.println("Socket is closed.");
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Cannot close socket.");
    }
  }

  public void setOut(PrintWriter out) {
    this.out = out;
  }

  public void setSocket(Socket socket) {
    this.socket = socket;
  }

  public void setServerAddress(String serverAddress) {
    this.serverAddress = serverAddress;
  }

  public void setPORT(int PORT) {
    this.PORT = PORT;
  }
}
