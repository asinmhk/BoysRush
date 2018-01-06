//package main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SiWa extends  CalabashBoy {
    SiWa() {
        super(NAME.Siwa, CBCOLOR.GREEN, 100, 20, -3);

        initImage();
    }

    @Override
    protected void initImage() {
        URL loc = this.getClass().getClassLoader().getResource("images/siwastand1.png");
        ImageIcon iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[0] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/siwastand2.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[1] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/siwarun1.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[2] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/siwarun2.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[3] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/siwarun3.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[4] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/siwarun4.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[5] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/siwaattack1.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[6] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/siwaattack2.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[7] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/siwaattack3.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[8] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/siwadead1.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[9] = iia.getImage();
        image = imageArray[0];
    }

    @Override
    public double attack(Map map) {

        int x = position.getX(), y = position.getY(), z = position.getZ();

        Creature temp;

        for (int i = 0; i < 5; i++) {
            temp = map.getCreature(new Position3D(x - i - 1, y, z));

            if (temp != null) {
                if (temp.getCamp() != camp) {
                    if (temp.getState() != STATE.Dead) {
                        double result = temp.beAttacked(data.getDamage());

                        return result;
                    }
                }
            }
        }
        return -3;
    }
}
