//package main;

import java.util.concurrent.TimeUnit;

public class LoadCreatureExecutor implements Runnable {
    private Creature creature;

    private Map map;

    LoadCreatureExecutor(Creature creature, Map map) {
        this.creature = creature;
        this.map = map;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {

                creature.update();

                STATE state = creature.getState();
                if (state == STATE.None) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    continue;
                }

                if (state == STATE.Dead) {
                    creature.deadExecutor(map, true);
                    continue;
                }

                if (state != STATE.Wait && state != STATE.Attack && state != STATE.Move) {
                    double result = creature.attack(map);
                    if (result == -4)
                        continue;
                    else if (result == -5) {
                        creature.setState(STATE.Attack);
                        creature.setAttackTime(0);
                        continue;
                    }
                    else if (result != -3) {
                        creature.setState(STATE.Attack);
                        creature.setAttackTime(0);
                        continue;
                    }
                }

                if (state == STATE.Wait) {
                    creature.waitExecutor(map, true);
                    continue;
                }

                if (state == STATE.Attack) {
                    creature.attackExecutor(map, true);
                } else if (state == STATE.Move) {
                    creature.moveExecutor(map, true);
                } else {
                    creature.aliveExecutor(map, true);
                }

                //TimeUnit.MILLISECONDS.sleep(10);
                creature.update();

            }
        } catch (InterruptedException e) {
            //System.out.println("Exiting via interrupt");
        }
    }
}
