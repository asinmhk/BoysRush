//package main;

import java.awt.Image;

public class PlainMap implements Map {

    private int mx, my, mz;

    private Block[][][] map;

    private boolean[][][] lock;

    PlainMap() {
        mx = 20; my = 20; mz = 1;
        init();
    }

    public PlainMap(int x, int y, int z) {
        mx = x; my = y; mz = z;
        init();
    }

    public void init() {
        map = new Block[mz][my][mx];
        for( int i = 0; i < mz; i++)
            for (int j = 0; j < my; j++)
                for (int k = 0; k < mx; k++)
                    map[i][j][k] = new RealBlock();
        lock = new boolean[mz][my][mx];
        for( int i = 0; i < mz; i++)
            for (int j = 0; j < my; j++)
                for (int k = 0; k < mx; k++)
                    lock[i][j][k] = false;
    }

    public Block getInfo(Position position) {
        int x = position.getX(), y = position.getY(), z = position.getZ();
        return map[z][y][x];
    }

    public int getMaxX() { return mx; }

    public int getMaxY() { return my; }

    public int getMaxZ() { return mz; }

    public Block getBlock(Position position) {
        if (islegal(position))
            return getInfo(position);
        return null;
    }

    public Creature getCreature(Position position) {
        Block temp = getBlock(position);
        if (temp instanceof Creature)
            return (Creature)temp;
        return null;
    }

    public boolean islegal(Position position) {

        int x = position.getX(), y = position.getY(), z = position.getZ();

        return (x < mx && x >= 0 && y < my && y >= 0 && z < mz && z >= 0);

    }

    public boolean isoccupied(Position position) {

        int x = position.getX(), y = position.getY(), z = position.getZ();

        return map[z][y][x].isoccupied();

    }

    public void setblock(Block block, Position newposition) { map[newposition.getZ()][newposition.getY()][newposition.getX()] = block; }

    public boolean clearblock(Position position) {

        if (islegal(position)) {

            int x = position.getX(), y = position.getY(), z = position.getZ();
            map[z][y][x].positionreset();
            map[z][y][x] = new RealBlock();

            return true;

        }

        return false;

    }

    public void draw() {
        for( int i = 0; i < mz; i++)
            for (int j = 0; j < my; j++) {
                for (int k = 0; k < mx; k++) {

                    if (map[i][j][k].isoccupied())
                        System.out.printf(" %.2s ", map[i][j][k].getname().toString());//(map[i][j][k].getname().toString());
                    else
                        System.out.print(" ---- ");

                }

                System.out.println();

            }
    }

    public boolean doLock(Position position) {
        if (isoccupied(position))
            return false;
        int x = position.getX(), y = position.getY(), z = position.getZ();
        if (lock[z][y][x]) return false;
        lock[z][y][x] = true; return true;
    }

    public boolean unlock(Position position) {
        int x = position.getX(), y = position.getY(), z = position.getZ();
        if (!lock[z][y][x]) return false;
        lock[z][y][x] = false; return true;
    }

    public Image getImage(Position position) {
        int x = position.getX(), y = position.getY(), z = position.getZ();

        return map[z][y][x].getImage();

    }

    public boolean isTarget(Position position) {
        Block b = getBlock(position);
        if (b != null && b.occupiedCamp == CAMP.Good)
            return true;

        return false;
    }

}
