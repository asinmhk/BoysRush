//package main;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;

public class Container extends JFrame{

    Painter painter;

    Scene scene;

    TYPE type;

    private final int OFFSET = 30;

    public Container(Scene scene) { this.scene = scene; type = TYPE.Begin; initUI(); }

    public void initUI() {

        painter = new Painter();
        add(painter);
        painter.setMap(scene.getMap());

        addKeyListener(new TAdapter(painter, scene));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1024, 768);

        setLocationRelativeTo(null);
        setTitle("BoysRush");
    }

    public Painter getPainter() { return painter; }

}

class TAdapter extends KeyAdapter {

    Painter painter;

    Scene scene;

    public TAdapter(Painter painter, Scene scene) {
        this.painter = painter; this.scene = scene;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();


        if (key == KeyEvent.VK_SPACE) {

            if (painter.getType() == TYPE.Begin || painter.getType() == TYPE.End) {
                Recorder.clear();
                scene.gameInit();
                scene.addPainter(painter);
                scene.gameStart();

                String pathName = System.getProperty("user.dir") + "\\classes\\log.txt";
                File f = new File(pathName);
                try {
                    long time = System.currentTimeMillis();
                    byte[] bTime = new byte[8];
                    for (int i = 0; i < 8; ++i) {
                        int offset = 64 - (i + 1) * 8;
                        bTime[i] = (byte) ((time >> offset) & 0xff);
                    }
                    OutputStream out = new FileOutputStream(f);
                    out.write(bTime);
                    out.close();
                } catch (Exception ee) {
                }

                painter.setMap(scene.getMap());
                painter.game();
            }

        } else if (key == KeyEvent.VK_L) {
            if (painter.getType() == TYPE.Begin || painter.getType() == TYPE.End) {
                scene.gameInit();
                scene.addPainter(painter);
                String pathName = System.getProperty("user.dir") + "\\classes\\log.txt";
//                File file = new File(pathName);
//                if (!file.exists()) {
//                    file = new File(System.getProperty("user.dir"));
//                    String strParentDirectory = file.getParent();
//                    System.out.println(strParentDirectory);
//                }


                scene.loadInit(pathName);
                painter.setMap(scene.getMap());
                painter.load();
            }
        } else if (key == KeyEvent.VK_R) {
            if (painter.getType() == TYPE.Begin || painter.getType() == TYPE.End) {
                scene.gameInit();
                scene.addPainter(painter);
                String pathName = System.getProperty("user.dir") + "\\classes\\example.txt";
                scene.loadInit(pathName);
                painter.setMap(scene.getMap());
                painter.load();
            }
        }

    }

}