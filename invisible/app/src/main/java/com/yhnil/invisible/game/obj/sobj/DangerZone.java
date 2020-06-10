package com.yhnil.invisible.game.obj.sobj;

import android.graphics.Color;

import com.yhnil.invisible.framework.obj.ShapeObject;

public class DangerZone extends ShapeObject {
    public DangerZone(float x, float y) {
        super(x, y);
        //setSector();
        setColor(Color.BLUE);
    }

    public void update() {
    }
}
