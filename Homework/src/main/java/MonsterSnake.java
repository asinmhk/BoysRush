//package main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MonsterSnake extends Monster {

    MonsterSnake() {
        super(NAME.Snake, 100, 7, 0);

        initImage();
    }

    @Override
    protected void initImage() {
        URL loc = this.getClass().getClassLoader().getResource("images/snakestand1.png");
        ImageIcon iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[0] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/snakestand2.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[1] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/snakerun1.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[2] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/snakerun2.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[3] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/snakerun3.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[4] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/snakerun4.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[5] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/snakeattack1.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[6] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/snakeattack2.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[7] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/snakeattack3.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(160, 128, Image.SCALE_DEFAULT));
        imageArray[8] = iia.getImage();
        loc = this.getClass().getClassLoader().getResource("images/snakedead1.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        imageArray[9] = iia.getImage();
        image = imageArray[0];
    }


}
