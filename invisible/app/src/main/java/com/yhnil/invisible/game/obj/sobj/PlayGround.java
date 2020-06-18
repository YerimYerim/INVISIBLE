package com.yhnil.invisible.game.obj.sobj;

import com.yhnil.invisible.framework.iface.CircleCollidable;
import com.yhnil.invisible.framework.obj.ShapeObject;
import com.yhnil.invisible.framework.util.Vector;

public class PlayGround extends ShapeObject implements CircleCollidable {
    public PlayGround(float x, float y) {
        super(x, y);
        setCircle(100);
    }

    public void update() {
    }

    @Override
    public float getCircle(Vector position) {
       position.x = x;
       position.y = y;
       return getRadius()-20;
    }
}
