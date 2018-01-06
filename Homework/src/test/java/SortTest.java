//package test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class SortTest {

    private Map map;

    private CalabashBoy[] boys;

    private Monster[] monsters;

    @Before
    public void init() throws Exception {
        map = new PlainMap(64, 48, 1);

        boys = new CalabashBoy[7];
        for (int i = 0; i < boys.length; i++) {
            boys[i] = new CalabashBoy(NAME.values()[i + 1], CBCOLOR.values()[i]);
        }

        new EmbattleSort().sort(boys, map);

        monsters = new Monster[19];
        monsters[0] = new Monster(NAME.Centipede);
        for (int i = 1; i < monsters.length; i++)
            monsters[i] = new Monster(NAME.Scorpio);

        new EmbattleSort().sort(monsters, map, 1);
    }

    @Test
    public void sort() {

        int x = boys[0].getposition().getX(), y = boys[1].getposition().getY();

        for (int i = 1; i < boys.length; i++) {
            assertEquals(x, boys[i].getposition().getX());
            assertEquals(y, boys[i].getposition().getY());
            y += 3;
        }

        x = monsters[0].getposition().getX();
        y = monsters[0].getposition().getY();
        int offSet = 1;

        for (int i = 1; i < monsters.length; i++) {
            x--;
            assertEquals(x, monsters[i].getposition().getX());
            assertEquals(y - offSet, monsters[i].getposition().getY());
            i++;
            assertEquals(x, monsters[i].getposition().getX());
            assertEquals(y + offSet, monsters[i].getposition().getY());
            offSet++;
        }

    }
}
