import javafx.concurrent.Task;

public class DoWork extends Task<Integer> {

  int[][] fields;
  Board board;
public DoWork(int[][] fields, Board board) {
  this.fields = fields;
  this.board = board;
}
  @Override
  protected Integer call() throws Exception {
    int i = 0;
//    board.refresh(fields);
    while(i < 10) {
      fields[4+i][12] = 5;
      System.out.println(i);
      Thread.sleep(500);
      i++;
    }
    return 10;
  }
}
