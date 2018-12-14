import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class NewGame {
  // TODO Zmienić ewentualnie na ComboBox
  private PrintWriter out;
  private Socket socket;
  @FXML
  VBox layout;
  @FXML
  public void oneOneHandler(){
    out.println("NEW 1 1");
    closeStage();
  }
  @FXML
  public void oneThreeHandler() {
    out.println("NEW 1 3");
    closeStage();
  }
  @FXML
  public void twoHandler() {
    out.println("NEW 2 0");
    closeStage();

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

  private void closeStage() {
    try {
      socket.close();
      System.out.println("Socket is closed.");
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Cannot close socket.");
    }
    Stage stage = (Stage) layout.getScene().getWindow();
    stage.close();
  }

  public void setOut(PrintWriter out) {
    this.out = out;
  }

  public void setSocket(Socket socket) {
    this.socket = socket;
  }
}
