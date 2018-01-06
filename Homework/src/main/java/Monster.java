//package main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Monster extends Creature {

    public Monster(NAME givenname) {
        super(givenname, CAMP.Evil);
        data.init(100, 2, 0);
    }

    Monster(NAME givenname, double life, double damage, double armor) {
        super(givenname, CAMP.Evil);
        data.init(life, damage, armor);
    }

    @Override
    public void controlFunc(Map map) throws Exception {}

    @Override
    public void moveExecutor(Map map, boolean wait) throws InterruptedException {
        boolean xFinish = false, yFinish = false;
        int x = offX, y = offY;

        if (tagPosition.getX() == (position.getX() + x/64))
            xFinish = true;
        else if (tagPosition.getX() > (position.getX() + x/64))
            x += 1;
        else
            x -= 1;

        if (tagPosition.getY() == (position.getY() + y/64))
            yFinish = true;
        else if (tagPosition.getY() > (position.getY() + y/64))
            y += 1;
        else
            y -= 1;

        if (!xFinish) {
            if (x % 64 / 16 == 0)
                setImage(imageArray[2]);
            else if (x % 64 / 16 == 1)
                setImage(imageArray[3]);
            else if (x % 64 / 16 == 2)
                setImage(imageArray[4]);
            else
                setImage(imageArray[5]);
        }

        if (xFinish && yFinish) {
            Position temp = tagPosition;
            cleanTag();
            if (!moveto(map, temp)) {
                setState(STATE.Wait);
                waitTime = 0;
            }
            return;
        }

        setOff(x, y);

        if (wait)
            TimeUnit.MILLISECONDS.sleep(10);
    }

    @Override
    public  void attackExecutor(Map map, boolean wait) throws InterruptedException {
        if (attackTime < 20)
            setImage(imageArray[6]);
        else if (attackTime < 22)
            setImage(imageArray[7]);
        else if (attackTime < 32)
            setImage(imageArray[8]);

        attackTime = (attackTime + 1) % 32;
        if (attackTime == 0) {
            setState(STATE.Wait);
            waitTime = 0;

            if (wait)
                TimeUnit.MILLISECONDS.sleep(100);
        }

        if (wait)
            TimeUnit.MILLISECONDS.sleep(30);
    }

}
