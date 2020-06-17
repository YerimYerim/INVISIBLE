package com.yhnil.invisible.game.obj.sobj;

import android.graphics.Color;

import com.yhnil.invisible.framework.obj.ShapeObject;

public class CoreStone extends ShapeObject {

    public CoreStone(float x, float y, int color) {
        super(x, y);

        setPentagonUnit(20);
        setColor(color);
    }
}
