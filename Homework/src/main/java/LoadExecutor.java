//package main;

import java.util.concurrent.TimeUnit;

public class LoadExecutor implements Runnable  {

    private OperateUnit[] operates;

    private int next;

    private Map map;

    private Creature[] good;

    LoadExecutor(Map map, OperateUnit[] operate) {
        this.map = map;
        next = 0;
        //operatesInit();
        operates = operate;
    }


    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Load start, operateions: " + operates.length);
                //for (int i = 0; i < operates.length; i++)
                //    System.out.println(operates[i].getDelay() + " " + operates[i].getStart().getX() + " " + operates[i].getStart().getY() + " " + operates[i].getEnd().getX() + " " + operates[i].getEnd().getY());
                TimeUnit.MILLISECONDS.sleep(100);
                for (int i = 0; i < operates.length; i++) {

                    long wait = operates[i].getDelay();
                    TimeUnit.MILLISECONDS.sleep(wait);

                    for(int j = 0; j < 100; j++) {
                        Creature c = map.getCreature(operates[i].getStart());

                        if (c != null) {

                            if (c.getState() == STATE.Alive || c.getState() == STATE.Wait) {
                                Position temp = operates[i].getEnd();
                                if (temp.getX() == -1 && temp.getY() == -1) {
                                    double result = c.attack(map);
                                    if (result != -3 && result != -4) {
                                        c.setState(STATE.Attack);
                                        c.setAttackTime(0);
                                        break;
                                    }
                                } else if (map.islegal(temp) && !map.isoccupied(temp)) {
                                    if (c.moveTag(map, temp)) {
                                        break;
                                    }
                                }
                            }
                        }
                        TimeUnit.MILLISECONDS.sleep(10);
                        //System.out.println("Unexpected error");
                        //System.out.println(operates[i].getDelay() + " " + operates[i].getStart().getX() + " " + operates[i].getStart().getY() + " " + operates[i].getEnd().getX() + " " + operates[i].getEnd().getY());
                    }
                }

                System.out.println("Load: operations finish");
                TimeUnit.MILLISECONDS.sleep(6);

                return;

            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
    }

}

class OperateUnit {

    private long delay;

    private Position start;

    private Position end;

    OperateUnit(long delay, Position start, Position end) {
        this.delay = delay; this.start = start; this.end = end;
    }

    public long getDelay() { return delay; }

    public Position getStart() { return start; }

    public Position getEnd() { return end; }
}
