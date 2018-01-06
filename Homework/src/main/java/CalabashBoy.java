//package main;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CalabashBoy extends Creature implements Comparable {

    private CBCOLOR color;

    public CalabashBoy(NAME givenname, CBCOLOR givencolor) {

        super(givenname, CAMP.Good);

        color = givencolor;

        data.init(100, 20, 0);

        if (givenname == NAME.All) {
            URL loc = this.getClass().getClassLoader().getResource("images/standall1.png");
            ImageIcon iia = new ImageIcon(loc);
            iia.setImage(iia.getImage().getScaledInstance(128, 384, Image.SCALE_DEFAULT));
            imageArray[0] = iia.getImage();
            loc = this.getClass().getClassLoader().getResource("images/standall2.png");
            iia = new ImageIcon(loc);
            iia.setImage(iia.getImage().getScaledInstance(128, 384, Image.SCALE_DEFAULT));
            imageArray[1] = iia.getImage();
        }
    }

    CalabashBoy(NAME givenname, CBCOLOR givencolor, double life, double damage, double armor) {

        super(givenname, CAMP.Good);

        color = givencolor;

        data.init(life, damage, armor);
    }

    public CBCOLOR getcolor() { return color; }

    public boolean biggerthan(Comparable brother){

        if (brother instanceof  CalabashBoy)
            return this.getname().ordinal()> ((CalabashBoy) brother).getname().ordinal();
        else
            return false;
    }

    @Override
    public void controlFunc(Map map) throws Exception{

    }

//    @Override
//    public void aliveExecutor(Map map, boolean wait) throws InterruptedException {
//        if (aliveTime / 16 == 0) {
//            //URL loc = this.getClass().getClassLoader().getResource("image/standall1.png");
//            ImageIcon iia = new ImageIcon("src/main/java/images/standall1.png");
//            iia.setImage(iia.getImage().getScaledInstance(128, 384, Image.SCALE_DEFAULT));
//            setImage(iia.getImage());
//        }
//        else {
//            //URL loc = this.getClass().getClassLoader().getResource("image/standall2.png");
//            ImageIcon iia = new ImageIcon("src/main/java/images/standall2.png");
//            iia.setImage(iia.getImage().getScaledInstance(128, 384, Image.SCALE_DEFAULT));
//            setImage(iia.getImage());
//        }
//        aliveTime = (aliveTime + 1) % 32;
//        if (wait)
//            TimeUnit.MILLISECONDS.sleep(20);
//    }

    @Override
    public void moveExecutor(Map map, boolean wait) throws InterruptedException {
        boolean xFinish = false, yFinish = false;

        if (tagPosition.getX() == (position.getX()))
            xFinish = true;
        else if (tagPosition.getX() > (position.getX()))
            offX += 1;
        else
            offX -= 1;

        if (tagPosition.getY() == (position.getY()))
            yFinish = true;
        else if (tagPosition.getY() > (position.getY()))
            offY += 1;
        else
            offY -= 1;

        if (!xFinish) {
            if (offX / 16 == 0)
                setImage(imageArray[2]);
            else if (offX / 16 == 1 || offX / 16 == -1)
                setImage(imageArray[3]);
            else if (offX / 16 == 2 || offX / 16 == -2)
                setImage(imageArray[4]);
            else
                setImage(imageArray[5]);
        } else {
            if (offY / 16 == 0)
                setImage(imageArray[2]);
            else if (offY / 16 == 1 || offY / 16 == -1)
                setImage(imageArray[3]);
            else if (offY / 16 == 2 || offY / 16 == -2)
                setImage(imageArray[4]);
            else
                setImage(imageArray[5]);
        }

        if (offX == 64 || offY == 64 || offX == -64 || offY == -64) {
            int x = position.getX() + offX/64, y = position.getY() + offY/64;
            Position temp = new Position3D(x, y, 0);
            offX = offX % 64; offY = offY % 64;
            moveto(map, temp);
        }

        if (xFinish && yFinish) {
            Position temp = tagPosition;
            cleanTag();
            if (!moveto(map, temp)) {
                setState(STATE.Wait);
                waitTime = 0;
            }
        }

        if (wait)
            TimeUnit.MILLISECONDS.sleep(10);
    }

    @Override
    public void attackExecutor(Map map, boolean wait) throws InterruptedException {
        if (attackTime < 15)
            setImage(imageArray[6]);
        else if (attackTime < 17)
            setImage(imageArray[7]);
        else if (attackTime > 22)
            setImage(imageArray[8]);

        attackTime = (attackTime + 1) % 40;
        if (attackTime == 0) {
            setState(STATE.Wait);
            waitTime = 0;
            if (wait)
                TimeUnit.MILLISECONDS.sleep(100);
        }
        if (wait)
            TimeUnit.MILLISECONDS.sleep(10);
    }
}
//
//enum CBCOLOR {
//    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, PURPLE
//}

