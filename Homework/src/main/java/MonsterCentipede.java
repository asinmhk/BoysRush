//package main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MonsterCentipede extends Monster {


    MonsterCentipede() {
        super(NAME.Centipede, 100, 5, 0);

        initImage();
    }

    @Override
    protected void initImage() {
        URL loc = this.getClass().getClassLoader().getResource("images/wugong3.png");
        ImageIcon iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[0] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/wugong1.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[1] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/wugong1.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[2] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/wugong2.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[3] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/wugong3.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[4] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/wugong4.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[5] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/wugongattack1.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[6] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/wugongattack2.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[7] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/wugongattack3.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[8] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/wugongdead1.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[9] = iia.getImage();
        image = imageArray[0];
    }

}
