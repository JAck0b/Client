import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.NormalBoard;

public class Main extends Application {

  static int[][] fields;

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
    Scene scene = new Scene(root, 200, 250);
    primaryStage.setTitle("Chinese Checkers");
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  public static void main(String[] args) {
    int number_of_players = 6;
    NormalBoard nb = new NormalBoard(number_of_players);
    Board.writefields(nb.fields);
    Board.number_of_players = number_of_players;
    launch(args);
  }
}
