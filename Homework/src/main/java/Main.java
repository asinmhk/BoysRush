//package main;

public class Main {

    public static void main(String[] args){

        Scene scene = new Scene();

        scene.beginInit();

        Container container = new Container(scene);

        scene.addPainter(container.getPainter());

        container.setVisible(true);

        container.setResizable(false);

        scene.beginStart();

    }

}

