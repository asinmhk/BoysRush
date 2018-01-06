//package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;

public class Painter extends JPanel {

    private Map map;

    public static TYPE type = TYPE.Begin;

    public static boolean mouseEnable = false;

    MyMouseListener mouseListener;

    int mx;
    int my;

    Painter() {
        mouseListener = new MyMouseListener(map);
        addMouseListener(mouseListener);
    }

    public void setMap(Map map) {
        this.map = map;
        mx = map.getMaxX();
        my = map.getMaxY();
        mouseListener.setMap(map);
    }

    public static TYPE getType() { return type; }

    //public static void begin() { type = TYPE.Begin; mouseEnable = false; }

    public static void game() { type = TYPE.Game; mouseEnable = true; }

    public static void end() { type = TYPE.End; mouseEnable = true; }

    public static void load() { type = TYPE.Load; mouseEnable = false; }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //buildWorld(g);

        if (type == TYPE.Begin)
            Paint(g);
        else if (type == TYPE.Game)
            Paint(g);
        else if (type == TYPE.Load)
            Paint(g);
        else if (type == TYPE.End)
            endPaint(g);
    }

    public void Paint(Graphics g) {
        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        URL loc = this.getClass().getClassLoader().getResource("images/background.jpg");
        ImageIcon iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(1024, 615, Image.SCALE_DEFAULT));
        g.drawImage(iia.getImage(), 0, 0, this);

        mapPaint(g);

        loc = this.getClass().getClassLoader().getResource("images/background2.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(1024, 615, Image.SCALE_DEFAULT));
        g.drawImage(iia.getImage(), 0, 0, this);
        loc = this.getClass().getClassLoader().getResource("images/text.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(1020, 120, Image.SCALE_DEFAULT));
        g.drawImage(iia.getImage(), 0, 615, this);
        if (type == TYPE.Begin) {
            loc = this.getClass().getClassLoader().getResource("images/text0.png");
            iia = new ImageIcon(loc);
            iia.setImage(iia.getImage().getScaledInstance(400, 45, Image.SCALE_DEFAULT));
            g.drawImage(iia.getImage(), 350, 650, this);
        }
        else if (type == TYPE.Game || type == TYPE.Load) {
            Creature s = MyMouseListener.getSelectC();

            if (s != null) {
                if (s.getname() == NAME.Dawa) {
                    loc = this.getClass().getClassLoader().getResource("images/text4.png");
                    iia = new ImageIcon(loc);
                    iia.setImage(iia.getImage().getScaledInstance(650, 45, Image.SCALE_DEFAULT));
                    g.drawImage(iia.getImage(), 280, 650, this);
                } else if (s.getname() == NAME.Siwa) {
                    loc = this.getClass().getClassLoader().getResource("images/text5.png");
                    iia = new ImageIcon(loc);
                    iia.setImage(iia.getImage().getScaledInstance(650, 45, Image.SCALE_DEFAULT));
                    g.drawImage(iia.getImage(), 320, 650, this);
                } else if (s.getname() == NAME.Wuwa) {
                    loc = this.getClass().getClassLoader().getResource("images/text6.png");
                    iia = new ImageIcon(loc);
                    iia.setImage(iia.getImage().getScaledInstance(650, 45, Image.SCALE_DEFAULT));
                    g.drawImage(iia.getImage(), 240, 650, this);
                }
            } else {
                loc = this.getClass().getClassLoader().getResource("images/text1.png");
                iia = new ImageIcon(loc);
                iia.setImage(iia.getImage().getScaledInstance(650, 45, Image.SCALE_DEFAULT));
                g.drawImage(iia.getImage(), 250, 650, this);
            }
        }
    }

    public void endPaint(Graphics g) {
        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        URL loc = this.getClass().getClassLoader().getResource("images/background.jpg");
        ImageIcon iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(1024, 615, Image.SCALE_DEFAULT));
        g.drawImage(iia.getImage(), 0, 0, this);

        loc = this.getClass().getClassLoader().getResource("images/background2.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(1024, 615, Image.SCALE_DEFAULT));
        g.drawImage(iia.getImage(), 0, 0, this);
        loc = this.getClass().getClassLoader().getResource("images/text.png");
        iia = new ImageIcon(loc);
        iia.setImage(iia.getImage().getScaledInstance(1020, 120, Image.SCALE_DEFAULT));
        g.drawImage(iia.getImage(), 0, 615, this);

        if (MonsterExecutor.finish) {
            loc = this.getClass().getClassLoader().getResource("images/text3.png");
            iia = new ImageIcon(loc);
            iia.setImage(iia.getImage().getScaledInstance(650, 80, Image.SCALE_DEFAULT));
            g.drawImage(iia.getImage(), 230, 630, this);
        } else {
            loc = this.getClass().getClassLoader().getResource("images/text2.png");
            iia = new ImageIcon(loc);
            iia.setImage(iia.getImage().getScaledInstance(650, 80, Image.SCALE_DEFAULT));
            g.drawImage(iia.getImage(), 230, 630, this);
        }
    }

    public void mapPaint(Graphics g) {

        if (MonsterExecutor.finish) {
            System.out.println("end");
            if (type == TYPE.Game) {
                try {
                    Recorder.record(System.getProperty("user.dir") + "\\classes\\log.txt");
                } catch (Exception e) {
                    System.out.print("Record error");
                }
            }
            type = TYPE.End;
            return;
        }

        Creature s = MyMouseListener.getSelectC();
        if (s != null) {
            URL loc = this.getClass().getClassLoader().getResource("images/select.png");
            ImageIcon iia = new ImageIcon(loc);
            iia.setImage(iia.getImage().getScaledInstance(64, 30, Image.SCALE_DEFAULT));
            g.drawImage(iia.getImage(), (s.getposition().getX() - 2) * 64 + s.getOffX(), s.getposition().getY() * 64 + 260 + s.getOffY(), this);
        }

        for (int y = 0; y < my; y++) {
            for (int x = 0; x < mx; x++) {
                Position position = new Position3D(x, y, 0);
                if (map.isoccupied(position)) {
                    Image image = map.getImage(position);
                    Creature c = map.getCreature(position);
                    if (image != null)
                        g.drawImage(image, (x - 2) * 64 + c.getOffX() - 32, y * 64 + 170 + c.getOffY(), this);
                    if (type == TYPE.Game || type == TYPE.Load) {
                        URL loc = this.getClass().getClassLoader().getResource("images/life1.png");
                        ImageIcon iia;
                        iia = new ImageIcon(loc);
                        if (c.getCamp() == CAMP.Good) {
                            loc = this.getClass().getClassLoader().getResource("images/life3.png");
                            iia = new ImageIcon(loc);
                        }
                        double life = c.getLife() / 100;
                        int width = (int) (life * 60);
                        if (width != 0) {
                            iia.setImage(iia.getImage().getScaledInstance(width, 20, Image.SCALE_DEFAULT));
                            g.drawImage(iia.getImage(), (x - 2) * 64 + c.getOffX() + 2, y * 64 + 170 + c.getOffY(), this);
                        }
                        loc = this.getClass().getClassLoader().getResource("images/life2.png");
                        iia = new ImageIcon(loc);
                        iia.setImage(iia.getImage().getScaledInstance(60, 20, Image.SCALE_DEFAULT));
                        g.drawImage(iia.getImage(), (x - 2) * 64 + c.getOffX() + 2, y * 64 + 170 + c.getOffY(), this);

                        if (c.getIsCure()) {
                            loc = this.getClass().getClassLoader().getResource("images/cure.png");
                            iia = new ImageIcon(loc);
                            iia.setImage(iia.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
                            g.drawImage(iia.getImage(), (x - 2) * 64 + c.getOffX() - 32, y * 64 + 170 + c.getOffY(), this);
                        }
                    }

                    if (c.getCamp() == CAMP.Evil) {
                        if (type == TYPE.Game || type == TYPE.Load) {
                            if (x == mx - 1) {
                                System.out.println("end");
                                if (type == TYPE.Game) {
                                    try {
                                        Recorder.record(System.getProperty("user.dir") + "\\classes\\log.txt");
                                    } catch (Exception e) {
                                        System.out.print("Record error");
                                    }
                                }
                                type = TYPE.End;
                            }
                        }
                    }
                }
            }
        }
    }
}

class MyMouseListener implements MouseListener {

    Map map;

    static Creature selectC;

    MyMouseListener(Map map) { this.map = map; selectC = null; }

    public void setMap(Map map) { this.map = map; selectC = null; }

    public static Creature getSelectC() { return selectC; }

    public void mousePressed(MouseEvent e) {
        //System.out.println("press");
    }

    public void mouseClicked(MouseEvent e) {

        int x = calX(e), y = calY(e);
        if (y == 3)
            y = 2;
        if (y == -1)
            y = 0;

        if(e.getButton() == e.BUTTON1) {

            Creature c = map.getCreature(new Position3D(x, y, 0));

            if (c != null && c.getCamp() == CAMP.Good)
                selectC = c;
            else {
                if (selectC == null)
                    return;

                Position newPosition = new Position3D(x, y, 0);
                if (selectC.getState() == STATE.Alive || selectC.getState() == STATE.Wait ) {
                    if (map.islegal(newPosition) && !map.isoccupied(newPosition)) {
                        if (!Painter.mouseEnable || Painter.type == TYPE.Load)
                            return;
                        selectC.moveTag(map, newPosition);

                    }
                }
            }

        }
        else if(e.getButton() == e.BUTTON3) {
            selectC = null;
        }

    }

    public void mouseReleased(MouseEvent e) {
        //System.out.println("release");
    }

    public void mouseEntered(MouseEvent e) {
        //System.out.println("enter");
    }

    public void mouseExited(MouseEvent e) {
        //System.out.println("exit");
    }

    public int calX(MouseEvent e) {
        return e.getX() / 64 + 2;
    }

    public int calY(MouseEvent e) { return (e.getY() - 200) / 64; }
}

enum TYPE { Begin, Game, End, Load}