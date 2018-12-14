import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Test {
  private BufferedReader in;
  private PrintWriter out;
  private Socket socket;
  @FXML
  TextField adresServer;
  @FXML
  VBox layout;
  @FXML
  TextField status;

  @FXML
  public void connectHandler() {
    String serverAdress = adresServer.getText();
    status.setText(serverAdress);
    try {
      socket = new Socket(serverAdress, 8888);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
      System.out.println(in.readLine());
    } catch (Exception e) {
      status.setText("Nie połączono");
    }
  }

  @FXML
  public void openHandler() throws Exception {
    Stage primaryStage = (Stage) layout.getScene().getWindow();
    Stage stage = new Stage();
    stage.initOwner(primaryStage);
    stage.initModality(Modality.NONE);
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("clientTest.fxml"));
    Parent root = fxmlLoader.load();
    ClientTest clientTest = fxmlLoader.<ClientTest>getController();
    clientTest.setIn(in);
    clientTest.setOut(out);
    Scene scene = new Scene(root,400, 400);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
  }

}
