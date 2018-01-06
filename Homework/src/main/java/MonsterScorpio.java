//package main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MonsterScorpio extends Monster{

    MonsterScorpio() {
        super(NAME.Scorpio, 100, 9, 5);

        initImage();
    }

    @Override
    protected void initImage() {
        URL loc = this.getClass().getClassLoader().getResource("images/xiezistand1.png");
        ImageIcon iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[0] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/xiezistand2.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(150, 128, Image.SCALE_DEFAULT));
        imageArray[1] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/xiezi1.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[2] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/xiezi2.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[3] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/xiezi3.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[4] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/xiezi4.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[5] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/xieziattack1.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[6] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/xieziattack3.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(150, 128, Image.SCALE_DEFAULT));
        imageArray[7] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/xieziattack4.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(160, 128, Image.SCALE_DEFAULT));
        imageArray[8] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/xiezidead1.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[9] = iia.getImage();
        image = imageArray[0];
    }


}
