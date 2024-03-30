import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
  @Test
  public void test()
  {
    Main pg = new Main();
    String result = pg.print(94);
    assertEquals("You got an A!", result);
  }
}