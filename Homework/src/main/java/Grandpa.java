//package main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Grandpa extends Creature{

    public Grandpa() {
        super(NAME.Yeye, CAMP.Good);
        URL loc = this.getClass().getClassLoader().getResource("images/grandpa1.png");
        ImageIcon iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        image = iia.getImage();
    }

    @Override
    protected void initImage() {

    }

    @Override
    public void controlFunc(Map map) throws Exception {

    }

    @Override
    public void aliveExecutor(Map map, boolean wait) throws InterruptedException { }

    @Override
    public void moveExecutor(Map map, boolean wait) throws InterruptedException {
        boolean xFinish = false, yFinish = false;

        if (tagPosition.getX() == (position.getX() + offX/64))
            xFinish = true;
        else if (tagPosition.getX() > (position.getX() + offX/64))
            offX += 1;
        else
            offX -= 1;

        if (tagPosition.getY() == (position.getY() + offY/64))
            yFinish = true;
        else if (tagPosition.getY() > (position.getY() + offY/64))
            offY += 1;
        else
            offY -= 1;

        if (!xFinish) {
            if (offX % 64 / 16 == 0) {
                URL loc = this.getClass().getClassLoader().getResource("images/grandpa2.png");
                ImageIcon iia = new ImageIcon(loc);
                iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
                setImage(iia.getImage());
            } else if (offX % 64 / 16 == 1) {
                URL loc = this.getClass().getClassLoader().getResource("images/grandpa3.png");
                ImageIcon iia = new ImageIcon(loc);
                iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
                setImage(iia.getImage());
            } else if (offX % 64 / 16 == 2) {
                URL loc = this.getClass().getClassLoader().getResource("images/grandpa4.png");
                ImageIcon iia = new ImageIcon(loc);
                iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
                setImage(iia.getImage());
            } else {
                URL loc = this.getClass().getClassLoader().getResource("images/grandpa5.png");
                ImageIcon iia = new ImageIcon(loc);
                iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
                setImage(iia.getImage());
            }
        }

        if (offX == 64) {
            offX -= 64;
            moveto(map, new Position3D(position.getX() + 1, position.getY(), 0));
        }

        if (xFinish && yFinish) {
            Position temp = tagPosition;
            cleanTag();
            moveto(map, temp);
            return;
        }

        if (wait)
            TimeUnit.MILLISECONDS.sleep(10);
    }

}
