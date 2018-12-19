import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MenuTest {

  @Test
  public void exitHandler() {
    Menu menu = mock(Menu.class);
    menu.exitHandler();
    verify(menu).exitHandler();
  }

  @Test
  public void playHandler() {
    Menu menu = mock(Menu.class);
    menu.playHandler();
    verify(menu).playHandler();
  }

  @Test
  public void helphandler() {
    Menu menu = mock(Menu.class);
    menu.helpHandler();
    verify(menu).helpHandler();
  }

  @Test
  public void settingHandler() {
    Menu menu = mock(Menu.class);
    menu.settingHandler();
    verify(menu).settingHandler();
  }
}