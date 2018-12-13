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
  Button play;


  @FXML
  public void playHandler(ActionEvent e) {
    //TODO uruchamia okno / dialog z wyborem gier
    Stage primaryStage = (Stage) layout.getScene().getWindow();
    Stage stage = new Stage();
    stage.initOwner(primaryStage);
    stage.initModality(Modality.WINDOW_MODAL);
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("play.fxml"));
    Parent root = fxmlLoader.getController();
    Scene scene = new Scene(root, 500, 600);
    stage.setScene(scene);
    stage.setTitle("Games");
    stage.setResizable(false);
    stage.show();
  }

  @FXML
  public void connectHandler() {

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
    FXMLLoader fxmlLoader  = new FXMLLoader(getClass().getResource("board.fxml"));
    Parent root = fxmlLoader.load();
    Board controller = fxmlLoader.<Board>getController();
    controller.setTextek("Działam i mam sie dobrze :)");
    Scene scene = new Scene(root, 560, 750);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.setTitle("Board");
    stage.show();
  }
}
