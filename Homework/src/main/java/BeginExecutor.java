//package main;

import java.util.concurrent.TimeUnit;

public class BeginExecutor implements Runnable {

    Map map;

    Creature[] good;

    Creature[] evil;

    boolean init;

    boolean evilStart1;

    boolean evilStart2;

    boolean boysStart;

    public BeginExecutor(Map map, Creature[] good, Creature[] evil) {
        this.map = map; this.good = good; this.evil = evil;
        init = true; evilStart1 = true; evilStart2 = true; boysStart = true;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                if (Painter.getType() != TYPE.Begin)
                    return;

                good[0].update();

                if (init) {
                    good[0].moveTag(map, new Position3D(19, 1, 0));
                    init = false;
                }

                Position position = good[0].getposition();
                int x = good[0].getposition().getX();

                if (position.getX() < 19)
                    good[0].moveExecutor(map, false);

                if (evilStart1 && x > 8) {
                    evil[1].setposition(map, new Position3D(0, 1, 0));
                    evil[1].moveTag(map, new Position3D(8, 1, 0));
                    evilStart1 = false;
                }

                if (!evilStart1) {
                    if (evil[1].getState() == STATE.Move)
                        evil[1].moveExecutor(map, false);
                    else {
                        evil[1].aliveExecutor(map, false);
                        if (boysStart) {
                            good[1].setposition(map, new Position3D(12, 0, 0));
                            boysStart = false;
                        }
                    }
                }


                if (evilStart2 && x > 9) {
                    evil[0].setposition(map, new Position3D(0, 0, 0));
                    evil[0].moveTag(map, new Position3D(7, 0, 0));
                    evil[2].setposition(map, new Position3D(0, 2, 0));
                    evil[2].moveTag(map, new Position3D(7, 2, 0));
                    evilStart2 = false;
                }

                if (!evilStart2) {
                    if (evil[0].getState() == STATE.Move)
                        evil[0].moveExecutor(map, false);
                    else
                        evil[0].aliveExecutor(map, false);
                    if (evil[2].getState() == STATE.Move)
                        evil[2].moveExecutor(map, false);
                    else {
                        evil[2].aliveExecutor(map, false);
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                }

                if (!boysStart) {
                    good[1].aliveExecutor(map, false);
                }

                TimeUnit.MILLISECONDS.sleep(6);
                //creature.update();

            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
    }
}
