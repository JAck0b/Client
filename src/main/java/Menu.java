import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Menu {
  @FXML
  VBox layout;
  @FXML
  Button connect;
  @FXML
  Button join;
  @FXML
  Button newGame;

  @FXML
  public void connectHandler(ActionEvent e) {
    join.setDisable(false);
    newGame.setDisable(false);
  }

  @FXML
  public void exitHandler(ActionEvent e) {
    System.exit(0);
  }

  @FXML
  public void helpHandler(ActionEvent e) throws Exception {
    Stage primaryStage = (Stage) layout.getScene().getWindow();
    Stage stage = new Stage();
    stage.initOwner(primaryStage);
    stage.initModality(Modality.WINDOW_MODAL);
    Parent root = FXMLLoader.load(getClass().getResource("board.fxml"));
    Scene scene = new Scene(root, 600, 800);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.setTitle("Board");
    stage.show();
  }
}
