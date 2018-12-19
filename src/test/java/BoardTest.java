import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BoardTest {

  Board board;
  PrintWriter out;
  BufferedReader in;
  Socket socket;

  @Before
  public void setUp() {
    board = mock(Board.class);
    out = mock(PrintWriter.class);
    in = mock(BufferedReader.class);
    socket = mock(Socket.class);
  }

  @Test
  public void skipHandler() {
    board.skipHandler();
    verify(board).skipHandler();
  }

  @Test
  public void getOut() {
    board.getOut();
    verify(board).getOut();
  }

  @Test
  public void getIn() {
    board.getIn();
    verify(board).getIn();
  }

  @Test
  public void refresh() {
    board.refresh();
    verify(board).refresh();
  }

  @Test
  public void setOut() {
    board = new Board();
    board.setOut(out);
    assertEquals(out, board.getOut());
  }

  @Test
  public void setIn() {
    board = new Board();
    board.setIn(in);
    assertEquals(in, board.getIn());
  }

  @Test
  public void getSocket() {
    board = new Board();
    board.setSocket(socket);
    assertEquals(socket, board.getSocket());
  }

  @Test
  public void setSocket() {
    board = new Board();
    board.setSocket(socket);
    assertEquals(socket, board.getSocket());
  }
}