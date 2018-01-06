//package main;

import java.util.concurrent.TimeUnit;

public class CreatureExecutor implements Runnable {
    private Creature creature;

    private Map map;

    CreatureExecutor(Creature creature, Map map) {
        this.creature = creature;
        this.map = map;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {

                creature.update();
                //motionUpdate();

                STATE state = creature.getState();
                if (state == STATE.None) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    continue;
                }

                if (state == STATE.Dead) {
                    creature.deadExecutor(map, true);
                    continue;
                }

                if (Painter.getType() == TYPE.Game) {
                    if (state != STATE.Wait && state != STATE.Attack && state != STATE.Move) {
                        double result = creature.attack(map);
                        if (result == -4)
                            continue;
                        else if (result == -5) {

                            Recorder.add(System.currentTimeMillis(), new Position3D(creature.getposition().getX(), creature.getposition().getY(), 0), new Position3D(-1, -1, 0));
                            creature.setState(STATE.Attack);
                            creature.setAttackTime(0);
                            continue;
                        }
                        else if (result != -3) {
                            Recorder.add(System.currentTimeMillis(), new Position3D(creature.getposition().getX(), creature.getposition().getY(), 0), new Position3D(-1, -1, 0));
                            creature.setState(STATE.Attack);
                            creature.setAttackTime(0);
                            continue;
                        }
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
                    if (creature.getCamp() == CAMP.Good) {
                        creature.aliveExecutor(map, true);//aliveExecutor();
                    } else if (Painter.getType() == TYPE.Game){
                        Position position = creature.getposition();
                        Position nextPosition = new Position3D(position.getX() + 1, position.getY(), 0);
                        if (map.islegal(nextPosition) && !map.isoccupied(nextPosition)) {
                            if (!creature.moveTag(map, nextPosition))
                                creature.aliveExecutor(map, true);
                        } else
                            creature.aliveExecutor(map, true);
                    } else {
                        creature.aliveExecutor(map, true);
                    }
                }

                //TimeUnit.MILLISECONDS.sleep(10);
                creature.update();

            }
        } catch (InterruptedException e) {
            //System.out.println("Exiting via interrupt");
        }
    }
}
