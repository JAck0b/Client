import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientTest {
  private BufferedReader in;
  private PrintWriter out;
  @FXML
  TextField input;
  @FXML
  TextField output;
  @FXML
  Button button;

  @FXML
  public void buttonHandler() {
    out.println(input.getText());
    try {
      output.setText(in.readLine());
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Nie udało się");
    }
  }

  public void setIn(BufferedReader in) {
    this.in = in;
  }

  public void setOut(PrintWriter out) {
    this.out = out;
  }
}
