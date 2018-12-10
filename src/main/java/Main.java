import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    fields = new int[][]{
      {0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {4, 4, 4, 4, 1, 1, 1, 1, 1, 6, 6, 6, 6, 0, 0, 0, 0},
      {0, 4, 4, 4, 1, 1, 1, 1, 1, 1, 6, 6, 6, 0, 0, 0, 0},
      {0, 0, 4, 4, 1, 1, 1, 1, 1, 1, 1, 6, 6, 0, 0, 0, 0},
      {0, 0, 0, 4, 1, 1, 1, 1, 1, 1, 1, 1, 6, 0, 0, 0, 0},
      {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
      {0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 7, 0, 0, 0},
      {0, 0, 0, 0, 3, 3, 1, 1, 1, 1, 1, 1, 1, 7, 7, 0, 0},
      {0, 0, 0, 0, 3, 3, 3, 1, 1, 1, 1, 1, 1, 7, 7, 7, 0},
      {0, 0, 0, 0, 3, 3, 3, 3, 1, 1, 1, 1, 1, 7, 7, 7, 7},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0}
    };
    fields[9][12] = 4;
    Board.writeTmpTable(fields);
    launch(args);
  }
}
