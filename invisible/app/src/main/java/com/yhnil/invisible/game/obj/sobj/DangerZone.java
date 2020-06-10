package com.yhnil.invisible.game.obj.sobj;

import android.graphics.Color;

import com.yhnil.invisible.framework.obj.ShapeObject;

public class DangerZone extends ShapeObject {
    public DangerZone(float x, float y) {
        super(x, y);
        setColor(Color.MAGENTA);
        setSector(100);
    }

    public void update() {
    }
}
