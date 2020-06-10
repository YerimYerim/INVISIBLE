package com.yhnil.invisible.game.obj;

import android.graphics.Color;

import com.yhnil.invisible.framework.obj.ShapeObject;

public class Stone extends ShapeObject {
    public Stone(float x, float y) {
        super(x, y);
        setPentagon(10);
        setColor(Color.RED);
    }

    public void update() {
    }
}
