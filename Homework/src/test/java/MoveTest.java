
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MoveTest {

    Map map;

    Creature c1;

    Creature c2;

    @Before
    public void init() {
        map = new PlainMap(3,1,1);

        c1 = new CalabashBoy(NAME.Dawa, CBCOLOR.RED);
        c1.setposition(map, new Position3D(0, 0, 0));

        c2 = new CalabashBoy(NAME.Erwa, CBCOLOR.YELLOW);
        c2.setposition(map, new Position3D(2, 0, 0));

    }

    @Test
    public void move() {
        c1.moveto(map, new Position3D(1, 0, 0));
        c2.moveto(map, new Position3D(1, 0, 0));

        assertEquals(1, c1.getposition().getX());
        assertEquals(2, c2.getposition().getX());
    }

}


