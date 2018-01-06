//package main;

import java.util.concurrent.TimeUnit;

public class MonsterExecutor implements Runnable {

    Map map;

    Creature[] evil;

    public static boolean finish = false;

    public MonsterExecutor(Map map, Creature[] evil) {
        this.map = map; this.evil = evil;
        finish = false;
        evil[0].setposition(map, new Position3D(0, 0, 0));
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {

                Position p1 = new Position3D(0, 0, 0);
                Position p2 = new Position3D(0, 1, 0);
                Position p3 = new Position3D(0, 2, 0);


                evil[0].setState(STATE.Alive);
                evil[1].setposition(map, p3);
                TimeUnit.MILLISECONDS.sleep(5000);


                evil[1].setState(STATE.Alive);
                evil[2].setposition(map, p2);
                TimeUnit.MILLISECONDS.sleep(15000);


                evil[2].setState(STATE.Alive);
                evil[3].setposition(map, p1);
                TimeUnit.MILLISECONDS.sleep(4000);


                evil[3].setState((STATE.Alive));
                //TimeUnit.MILLISECONDS.sleep(500);
                //evil[4].setposition(map, p1);
                TimeUnit.MILLISECONDS.sleep(20000);


                //evil[4].setState((STATE.Alive));
                evil[5].setposition(map, p3);
                TimeUnit.MILLISECONDS.sleep(2000);


                evil[5].setState((STATE.Alive));
                evil[6].setposition(map, p2);
                TimeUnit.MILLISECONDS.sleep(12000);


                evil[6].setState((STATE.Alive));
                evil[7].setposition(map, p1);
                TimeUnit.MILLISECONDS.sleep(5000);


                evil[7].setState((STATE.Alive));
                //TimeUnit.MILLISECONDS.sleep(500);
                //evil[8].setposition(map, p2);
                TimeUnit.MILLISECONDS.sleep(10000);


                //evil[8].setState((STATE.Alive));
                TimeUnit.MILLISECONDS.sleep(2000);
                evil[9].setposition(map, p1);
                TimeUnit.MILLISECONDS.sleep(2000);


                evil[9].setState((STATE.Alive));
                evil[10].setposition(map, p2);
                TimeUnit.MILLISECONDS.sleep(13000);


                evil[10].setState((STATE.Alive));
                evil[11].setposition(map, p3);
                TimeUnit.MILLISECONDS.sleep(4000);


                evil[11].setState((STATE.Alive));
                evil[12].setposition(map, p1);
                TimeUnit.MILLISECONDS.sleep(12000);


                evil[12].setState((STATE.Alive));
                //TimeUnit.MILLISECONDS.sleep(500);
                //evil[13].setposition(map, p1);
                TimeUnit.MILLISECONDS.sleep(2000);


                //evil[13].setState((STATE.Alive));
                evil[14].setposition(map, p2);
                TimeUnit.MILLISECONDS.sleep(8000);


                evil[14].setState((STATE.Alive));
                evil[15].setposition(map, p3);
                TimeUnit.MILLISECONDS.sleep(3000);

                evil[15].setState((STATE.Alive));
                evil[16].setposition(map, p1);
                TimeUnit.MILLISECONDS.sleep(8000);

                evil[16].setState((STATE.Alive));
                TimeUnit.MILLISECONDS.sleep(2000);


                while (evil[16].getState() != STATE.Dead || evil[15].getState() != STATE.Dead || evil[14].getState() != STATE.Dead) {
                    TimeUnit.MILLISECONDS.sleep(2000);
                }

                finish = true;

                break;
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
    }
}
