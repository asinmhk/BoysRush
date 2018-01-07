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
                Recorder.setStartTime(System.currentTimeMillis());
                painter.setMap(scene.getMap());
                painter.game();
            }

        } else if (key == KeyEvent.VK_L) {
            if (painter.getType() == TYPE.Begin || painter.getType() == TYPE.End) {
                JFileChooser jfc=new JFileChooser(System.getProperty("user.dir"));
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
                jfc.showDialog(new JLabel(), "选择");
                jfc.setFileFilter(new MyFileFilter());
                File file=jfc.getSelectedFile();
                if (file.exists() && !file.isDirectory()) {
                    String pathName = file.getPath();
                    scene.gameInit();
                    scene.addPainter(painter);
                    //String pathName = System.getProperty("user.dir") + "\\classes\\log.txt";
                    scene.loadInit(pathName);
                    painter.setMap(scene.getMap());
                    painter.load();
                }
            }
        }

    }

}