
import java.awt.Image;

public interface Map {

    int getMaxX();

    int getMaxY();

    int getMaxZ();

    Block getBlock(Position position);

    Creature getCreature(Position position);

    boolean islegal(Position position);

    boolean isoccupied(Position position);

    void setblock(Block block, Position newposition);

    boolean clearblock(Position position);

    void draw();

    boolean doLock(Position position);

    boolean unlock(Position position);

    Image getImage(Position position);

    boolean isTarget(Position position);

}
