package com.yhnil.invisible.game.obj.sobj;

import android.graphics.Color;

import com.yhnil.invisible.framework.obj.ShapeObject;

public class Core extends ShapeObject {
    public Core(float x, float y) {
        super(x, y);
        setPentagon(10);
        setColor(Color.BLUE);
    }

    public void update() {
    }
}
