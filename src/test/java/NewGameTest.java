import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NewGameTest {

  NewGame newGame;
  PrintWriter out;
  Socket socket;

  @Before
  public void setUp() {
    newGame = mock(NewGame.class);
    out = mock(PrintWriter.class);
    socket = mock(Socket.class);
  }

  @Test
  public void initialize() {
    newGame.initialize();
    verify(newGame).initialize();
  }

  @Test
  public void playHandler() {
    newGame.playHandler();
    verify(newGame).playHandler();
  }

  @Test
  public void setOut() {
    newGame = new NewGame();
    newGame.setOut(out);
    assertEquals(newGame.getOut(), out);
  }

  @Test
  public void setSocket() {
    newGame = new NewGame();
    newGame.setSocket(socket);
    assertEquals(newGame.getSocket(), socket);
  }

  @Test
  public void setServerAddress() {
    newGame = new NewGame();
    newGame.setServerAddress("127.0.0.1");
    assertEquals(newGame.getServerAddress(), "127.0.0.1");
  }

  @Test
  public void setPORT() {
    newGame = new NewGame();
    newGame.setPORT(8888);
    assertEquals(newGame.getPORT(), 8888);
  }
}