//package main;

import java.awt.Image;

public abstract class Block {

    protected CAMP occupiedCamp;

    protected  boolean isTaged;

    boolean isoccupied() { return false; }

    abstract void positionreset();

    NAME getname() { return NAME.None; }

    CAMP getCamp() { return CAMP.None; }

    STATE getState() { return STATE.None; }

    boolean isCreature() { return false; }

    Image getImage() { return null;}

    void setOccupiedCamp(CAMP camp) { occupiedCamp = camp; }

    CAMP getOccupiedCamp() { return occupiedCamp; }

    void setTaged(boolean b) { isTaged = b; }

    boolean getTaged() { return isTaged; }
}

//enum NAME {
//    None, 大娃, 二娃, 三娃, 四娃, 五娃, 六娃, 七娃, 爷爷, 蛇精, 蝎子精, 蜈蚣精, 全体
//}

enum CAMP {
    None, Good, Evil, Netrule
}
