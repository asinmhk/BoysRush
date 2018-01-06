//package main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WuWa extends  CalabashBoy {

    private int cureTime;

    WuWa() {
        super(NAME.Wuwa, CBCOLOR.BLUE, 100, 5, 0);
        data.setCure(10);
        cureTime = 0;

        initImage();
    }

    @Override
    protected void initImage() {
        URL loc = this.getClass().getClassLoader().getResource("images/wuwastand1.png");
        ImageIcon iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[0] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/wuwastand2.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[1] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/wuwarun1.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[2] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/wuwarun2.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[3] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/wuwarun3.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[4] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/wuwarun4.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[5] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/wuwaattack1.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[6] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/wuwaattack2.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[7] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/wuwaattack3.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[8] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/wuwadead1.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[9] = iia.getImage();
        image = imageArray[0];
    }

    @Override
    public double attack(Map map) {

        int x = position.getX(), y = position.getY(), z = position.getZ();

        Creature temp;

        for (int i = 0; i < 2; i++) {
            temp = map.getCreature(new Position3D(x - 1 - i, y, z));

            if (temp != null) {
                if (temp.getCamp() != camp) {
                    if (temp.getState() != STATE.Dead) {
                        double result = temp.beAttacked(data.getDamage());
                        if (cureTime == 0) {
                            beCured(data.getCure());
                            cureTime = 800;
                        }
                        return result;
                    }
                }
            }
        }

        if (cureTime == 0) {
            boolean[] record = searchDirection(map, CAMP.Good, 1);
            boolean cure = false;

            if (record[0]) {
                temp = map.getCreature(new Position3D(x - 1, y, z));

                if (temp != null && temp.getCamp() == CAMP.Good && temp.getState() != STATE.Dead) {
                    if (temp.beCured(data.getCure()) != -1)
                        cure = true;
                }
            }
            if (record[2]) {
                temp = map.getCreature(new Position3D(x, y - 1, z));
                if (temp != null && temp.getCamp() == CAMP.Good && temp.getState() != STATE.Dead) {
                    if (temp.beCured(data.getCure()) != -1)
                        cure = true;
                }
            }
            if (record[1]) {
                temp = map.getCreature(new Position3D(x + 1, y, z));
                if (temp != null && temp.getCamp() == CAMP.Good && temp.getState() != STATE.Dead) {
                    if (temp.beCured(data.getCure()) != -1)
                        cure = true;
                }
            }
            if (record[3]) {
                temp = map.getCreature(new Position3D(x, y + 1, z));
                if (temp != null && temp.getCamp() == CAMP.Good && temp.getState() != STATE.Dead) {
                    if (temp.beCured(data.getCure()) != -1)
                        cure = true;
                }
            }

            if (cure) {
                beCured(data.getCure());
                cureTime = 800;
                return -5;
            }

            if (data.getLife() < 100) {
                beCured(data.getCure());
                cureTime = 800;
                return -5;
            }
        }

        return -3;
    }

    @Override
    public void update() {
        if (isDead) state = STATE.Dead;
        painter.repaint();
        if (cureTime > 0)
            cureTime--;
        if (isCure) {
            cureImageTime--;
            if (cureImageTime <= 0) {
                isCure = false;
                cureImageTime = 0;
            }
        }
    }
}
