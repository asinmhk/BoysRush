//package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Scene {

    private Map map;

    private ArrayList goodcreatures = new ArrayList();

    private ArrayList evilcreatures = new ArrayList();

    private ExecutorService exec;

    Scene() {

    }

    public void addPainter(Painter painter) {
        Creature[] creatures = new Creature[goodcreatures.size()];
        goodcreatures.toArray(creatures);
        for (int i = 0; i< goodcreatures.size(); i++)
            creatures[i].addPainter(painter);
        creatures = new Creature[evilcreatures.size()];
        evilcreatures.toArray(creatures);
        for (int i = 0; i< evilcreatures.size(); i++)
            creatures[i].addPainter(painter);
    }

    public Map getMap() { return map; }

    public void mapdraw() { map.draw(); }

//    public void test() {
//
//        Creature[] templine = new Creature[7];
//        Creature[] creatures = new Creature[goodcreatures.size()];
//        goodcreatures.toArray(creatures);
//        for (int i = 0; i < templine.length; i++)
//            templine[i] = creatures[i];
//        new EmbattleSort().sort(templine, map);
//
//        creatures = new Creature[evilcreatures.size()];
//        evilcreatures.toArray(creatures);
//        templine = new Creature[19];
//        for (int i = 0; i < templine.length; i++)
//            templine[i] = creatures[i + 1];
//
//        new EmbattleSort().sort(templine, map, 1);
//    }

    public void beginInit() {
        map = new PlainMap(20, 3, 1);
        Grandpa grandpa = new Grandpa();
        grandpa.setposition(map, new Position3D(1, 1, 0));
        goodcreatures.add(grandpa);

        goodcreatures.add(new CalabashBoy(NAME.All, CBCOLOR.RED));

        Monster[] monsters = new Monster[3];
        monsters[0] = new MonsterCentipede();
        monsters[1] = new MonsterScorpio();
        monsters[2] = new MonsterSnake();
        for (int i = 0; i < monsters.length; i++) {
            evilcreatures.add(monsters[i]);
        }
    }

    public void beginStart() {
        exec = Executors.newCachedThreadPool();
        Creature[] templine1 = new Creature[goodcreatures.size()];
        goodcreatures.toArray(templine1);
        Creature[] templine2 = new Creature[evilcreatures.size()];
        evilcreatures.toArray(templine2);
        exec.execute(new BeginExecutor(map, templine1, templine2));
    }

    public void gameInit() {
        exec.shutdownNow();

        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch(InterruptedException e) {}

        goodcreatures.clear();
        evilcreatures.clear();

        map = new PlainMap(19, 3, 1);

        CalabashBoy[] boys = new CalabashBoy[3];

        boys[0] = new DaWa();
        boys[0].setposition(map, new Position3D(12, 0, 0));
        boys[1] = new SiWa();
        boys[1].setposition(map, new Position3D(12, 1, 0));
        boys[2] = new WuWa();
        boys[2].setposition(map, new Position3D(12, 2, 0));

        int j;
        for (j = 0; j < boys.length; j++)
            goodcreatures.add(boys[j]);

        Monster[] monsters = new Monster[17];
        monsters[0] = new MonsterCentipede();
        monsters[1] = new MonsterCentipede();
        monsters[2] = new MonsterSnake();
        monsters[3] = new MonsterCentipede();
        monsters[4] = new MonsterCentipede();
        monsters[5] = new MonsterScorpio();
        monsters[6] = new MonsterCentipede();
        monsters[7] = new MonsterSnake();
        monsters[8] = new MonsterCentipede();
        monsters[9] = new MonsterCentipede();
        monsters[10] = new MonsterCentipede();
        monsters[11] = new MonsterScorpio();
        monsters[12] = new MonsterCentipede();
        monsters[13] = new MonsterCentipede();
        monsters[14] = new MonsterSnake();
        monsters[15] = new MonsterCentipede();
        monsters[16] = new MonsterScorpio();
        for (int i = 0; i < monsters.length; i++) {
            monsters[i].setState(STATE.None);
            evilcreatures.add(monsters[i]);
        }
    }

    public void gameStart() {
        exec = Executors.newCachedThreadPool();

        Creature[] templine = new Creature[goodcreatures.size()];
        goodcreatures.toArray(templine);
        for (int i = 0; i < goodcreatures.size(); i++)
            exec.execute(new CreatureExecutor(templine[i], map));

        templine = new Creature[evilcreatures.size()];
        evilcreatures.toArray(templine);
        for (int i = 0; i < evilcreatures.size(); i++)
            exec.execute(new CreatureExecutor(templine[i], map));

        exec.execute(new MonsterExecutor(map, templine));
    }

    public void loadInit(String pathName) {
        ArrayList operates = new ArrayList();
        loadData(operates, pathName);

        gameStart();

        OperateUnit[] temp = new OperateUnit[operates.size()];
        operates.toArray(temp);
        exec.execute(new LoadExecutor(map, temp));
    }

    public void loadData(ArrayList operates, String pathName) {

        File f = new File(pathName);
        long pre = 0;
        try {
            InputStream in = new FileInputStream(f);

            byte[] time = new byte[8];
            if (in.read(time) == -1) {
                System.out.println("Can not find data");
                return;
            }
            for (int i = 0; i < 8; i++) {
                pre <<= 8;
                pre |= (time[i] & 0xff);
            }

            byte[] b = new byte[12];
            while (in.read(b) != -1) {

                long delay = 0;
                for (int i = 0; i < 8; i++) {
                    delay <<= 8;
                    delay |= (b[i] & 0xff);
                }

                Position p1 = new Position3D(b[8], b[9], 0);
                Position p2 = new Position3D(b[10], b[11], 0);
                OperateUnit temp = new OperateUnit(delay - pre, p1, p2);
                operates.add(temp);

                pre = delay;

            }

            in.close();

        } catch (Exception e) {
            System.out.println("Error when loading file");
        }
    }

    void start() {

    }

}
