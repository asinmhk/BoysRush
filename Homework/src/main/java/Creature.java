//package main;

import javax.swing.*;
import java.awt.Image;
import java.util.concurrent.TimeUnit;

public abstract class Creature extends Block{

    protected Painter painter;

    protected Image image;

    protected Image[] imageArray;

    protected Position position;

    protected NAME name;

    protected CAMP camp;

    protected CreatureData data;

    protected STATE state;

    protected Position tagPosition;

    protected int aliveTime;

    protected int waitTime;

    protected int offX;

    protected int offY;

    protected int attackTime;

    protected int deadTime;

    protected boolean isDead;

    protected boolean isCure;

    protected int cureImageTime;

    public Creature(NAME givenname, CAMP givencamp) {

        position = new Position3D();

        name = givenname;

        camp = givencamp;

        data = new CreatureData();

        imageArray = new Image[10];

        state = STATE.Alive;

        isDead = false;
        isCure = false;

        aliveTime = 0;
        waitTime = 0;
        attackTime = 0;
        deadTime = 0;
        cureImageTime = 0;
    }

    @Override
    public boolean isoccupied() { return true; }

    @Override
    public boolean isCreature() { return true; }

    public boolean isCompany(Block block) { return camp == block.getCamp(); }

    public void setposition(Map map, Position newposition) {

        if (map.islegal(newposition) && !map.isoccupied(newposition)) {

            map.setblock(this, newposition);

            position.moveto(newposition);

        }

    }

    public void clearposition(Map map) { map.clearblock(position); }

    public Position getposition() { return position.getposition(); }

    public boolean moveto(Map map, Position newposition /** type */ ) {
        /**
         *  action allowed and map allowed
         */

        if (map.islegal(newposition) && !map.isoccupied(newposition)) {
            if (!map.doLock(newposition))
                return false;

            if (camp == CAMP.Evil) {
                if (map.isTarget(newposition)) {
                    map.unlock(newposition);
                    return false;
                }
                Block b = map.getBlock(newposition);
                if (b.getOccupiedCamp() == CAMP.Good) {
                    map.unlock(newposition);
                    return false;
                }
            }

            map.clearblock(position);
            map.clearblock(newposition);

            map.setblock(this, newposition);

            position.moveto(newposition);

            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Moving wait via interrupt");
            }

            map.unlock(newposition);

            return true;

        }

        return false;

    }

    @Override
    public Image getImage() { return image; }

    public void setImage(Image image) { this.image = image; }

    @Override
    public void positionreset() { position.clear(); }

    @Override
    public NAME getname() { return name; }

    @Override
    public CAMP getCamp() { return camp; }

    public void setState(STATE state) { this.state = state; }

    @Override
    public STATE getState() { return state; }

    public void addPainter(Painter painter) { this.painter = painter; }

    public abstract void controlFunc(Map map) throws Exception;

    public void update() {
        if (isDead) state = STATE.Dead;
        painter.repaint();
        if (isCure) {
            cureImageTime--;
            if (cureImageTime <= 0) {
                isCure = false;
                cureImageTime = 0;
            }
        }
    }

    public void moveStart() { state = STATE.Move; }

    public void moveFinish() { state = STATE.Alive; }

    public double getLife() { return data.getLife(); }

    public double getDamage() { return data.getDamage(); }

    public int getAliveTime() { return aliveTime; }

    public void setAliveTime(int a) { aliveTime = a; }

    public int getWaitTime() { return waitTime; }

    public void setWaitTime(int w) {waitTime = w; }

    public int getAttackTime() { return attackTime; }

    public void setAttackTime(int a) { attackTime = a; }

    public int getDeadTime() { return deadTime; }

    public void setDeadTime(int d) { deadTime = d; }

    public boolean getIsCure() { return isCure; }

    public double beCured(double curedata) {
        if (data.getLife() <= 0) {
            isDead = true;
            update();
            return -2;
        }

        double cure = data.beCured(curedata);

        if (cure > 0) {
            isCure = true;
            cureImageTime = 80;
        }

        return cure;
    }

    public double beAttacked(double damagedata) {
        if (data.getLife() <= 0) {
            isDead = true;
            update();
            return -2;
        }
        //miss
//        if (state == STATE.Move)
//            return -1;

        double damage = data.beAttacked(damagedata);

        if (data.getLife() <= 0) {
            isDead = true;
            update();
        }

        return damage;
    }

    public double attack(Map map) {

        int x = position.getX(), y = position.getY(), z = position.getZ();

        Creature temp;

        if (camp == CAMP.Good)
            temp = map.getCreature(new Position3D(x - 1, y, z));
        else
            temp = map.getCreature(new Position3D(x + 1, y, z));

        if (temp != null) {
            if (temp.getCamp() != camp) {
                if (temp.getState() != STATE.Dead) {
                    double result = temp.beAttacked(data.getDamage());

                    if (result == -1)
                        System.out.print("miss ");
                    else if (result == -2)
                        System.out.print("Dead ");
                    return result;
                }
            }
        } else if (camp == CAMP.Evil) {
            if (map.isTarget(new Position3D(x + 1, y, z))) {
                state = STATE.Wait;
                waitTime = 1;
                return -4;
            }

        }

        return -3;
    }

    public DIRECTIOIN moveDirection(Map map, CAMP c, int range) {

        for (int i = 0; i < range; i++) {
            boolean[] record = searchDirection(map, c, i);
            if (record[0]) return DIRECTIOIN.Left;
            if (record[2]) return DIRECTIOIN.Up;
            if (record[1]) return DIRECTIOIN.Right;
            if (record[3]) return DIRECTIOIN.Down;
        }

        return DIRECTIOIN.None;

    }

    protected boolean searchDirectionFunc(Map map, CAMP c, int range, DIRECTIOIN d) {
        int x = position.getX(), y = position.getY(), z = position.getZ();
        int start;
        if (d == DIRECTIOIN.Left) start = x - range;
        else if (d == DIRECTIOIN.Right) start = x + range;
        else if (d == DIRECTIOIN.Up) start = y - range;
        else start = y + range;

        for (int i = 0; i <= range; i++ ) {
            Creature temp;

            if (d == DIRECTIOIN.Left || d == DIRECTIOIN.Right)
                temp = map.getCreature(new Position3D(start, y + i, z));
            else
                temp = map.getCreature(new Position3D(x + i, start, z));
            if (temp != null)
                if (temp.getCamp() == c)
                    return true;

            if (d == DIRECTIOIN.Left || d == DIRECTIOIN.Right)
                temp = map.getCreature(new Position3D(start, y - i, z));
            else
                temp = map.getCreature(new Position3D(x - i, start, z));
            if (temp != null)
                if (temp.getCamp() == c)
                    return true;
        }

        return false;
    }

    protected boolean[] searchDirection(Map map, CAMP c, int range) {
        int x = position.getX(), y = position.getY(), z = position.getZ();

        boolean[] record = new boolean[4];

        for (int i = 0; i < 4; i++)
            record[i] = searchDirectionFunc(map, c, range, DIRECTIOIN.values()[i + 1]);

        return record;
    }

    public boolean moveTag(Map map, Position position) {
        if (Painter.getType() != TYPE.Begin && camp == CAMP.Evil && position.getX() > this.position.getX() + 1) {
            System.out.println("Unexpected moveTag");
            return false;
        }
        if (!moveCheck(map, position))
            return false;

        if (camp == CAMP.Good)
            blockTag(map, position);

        state = STATE.Move;
        tagPosition = position;
        offX = 0;
        offY = 0;
        if (Painter.getType() == TYPE.Game)
            Recorder.add(System.currentTimeMillis(), new Position3D(this.position.getX(), this.position.getY(), 0), new Position3D(position.getX(), position.getY(), 0));
        return true;
    }

    public void blockTag(Map map, Position p) {
        //map.getBlock(newPosition).setOccupiedCamp(CAMP.Good);
        int startX = position.getX(), startY = position.getY();
        int endX = p.getX(), endY = p.getY();

        while (startX != endX || startY != endY) {
            if (endX > startX) startX++;
            else if (endX < startX) startX--;
            if (endY > startY) startY++;
            else if (endY < startY) startY--;

            Position temp = new Position3D(startX, startY, 0);
            map.doLock(temp);
            Block b = map.getBlock(temp);
            b.setTaged(true);
            map.unlock(temp);
        }
        map.doLock(p);
        Block b = map.getBlock(p);
        b.setOccupiedCamp(CAMP.Good);
        map.unlock(p);
    }

    public boolean moveCheck(Map map, Position p) {
        int startX = position.getX(), startY = position.getY();
        int endX = p.getX(), endY = p.getY();

        while (startX != endX || startY != endY) {
            if (endX > startX) startX++;
            else if (endX < startX) startX--;
            if (endY > startY) startY++;
            else if (endY < startY) startY--;

            Position temp = new Position3D(startX, startY, 0);
            if (!map.islegal(temp))
                return false;
            if (map.isoccupied(temp) || map.isTarget(temp))
                return false;
            Block b = map.getBlock(temp);
            if (b.getOccupiedCamp() == CAMP.Good)
                return false;
            if (camp == CAMP.Evil)
                if (b.getTaged())
                    return false;
        }

        return true;
    }

    public void cleanTag() {
        state = STATE.Alive;
        waitTime = 0;
        tagPosition = null;
        offX = 0;
        offY = 0;
    }

    public Position getTagPosition() { return tagPosition; }

    public void setOff(int x, int y) { offX = x; offY = y; }

    public int getOffX() { return offX; }

    public int getOffY() { return offY; }

    protected void initImage() {}

    public void moveExecutor(Map map, boolean wait) throws InterruptedException {}

    public void aliveExecutor(Map map, boolean wait) throws InterruptedException {
        if (aliveTime / 16 == 0)
            setImage(imageArray[0]);
        else
            setImage(imageArray[1]);
        aliveTime = (aliveTime + 1) % 32;
        if (wait)
            TimeUnit.MILLISECONDS.sleep(20);
    }

    public void waitExecutor(Map map, boolean wait) throws InterruptedException {
        if (waitTime / 16 == 0)
            setImage(imageArray[0]);
        else
            setImage(imageArray[1]);
        waitTime = (waitTime + 1) % 32;
        if (waitTime == 0) {
            setState(STATE.Alive);
            setAliveTime(0);
        }

        if (wait)
            TimeUnit.MILLISECONDS.sleep(20);
    }

    public void attackExecutor(Map map, boolean wait) throws InterruptedException {}

    public void deadExecutor(Map map, boolean wait) throws InterruptedException {
        if (deadTime < 32)
            setImage(imageArray[9]);
        else
            clearposition(map);

        deadTime = deadTime + 1;

        if (wait)
            TimeUnit.MILLISECONDS.sleep(20);
    }
}

enum STATE {None, Wait, Alive, Dead, Move, Attack}

enum DIRECTIOIN {None, Left, Right, Up, Down}

