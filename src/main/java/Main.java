import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.Bot;
import logic.CheckMove;
import logic.NormalBoard;

public class Main extends Application {



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


    NormalBoard nb = new NormalBoard(6);
    nb.fields[9][12] =2;
    nb.fields[4][1] =3;
//    nb.fields[5][1] =2;

    nb.printArray();
    CheckMove checkMove = new CheckMove();
    checkMove.setFields(nb.fields);
    // checkMove.setXY(12,14);
//    System.out.println("Czy można na : " + checkMove.check_move(4,4));
    //   checkMove.printArray();
//    checkMove.printPath(checkMove.getPath(4,4));

    Bot bot = new Bot(checkMove.fields);
    bot.setId(2);
    // bot.find_destinaionXY();
    // System.out.println("DEST: " + bot.destinationX + " " +bot.destinationY);
    bot.calculate_best_move();

    Board.writeTmpTable(nb.getFields());
    launch(args);
  }
}
