package com.yhnil.invisible.game.obj.sobj;

import android.graphics.Color;

import com.yhnil.invisible.framework.obj.ShapeObject;

import java.util.Random;

public class Stone extends ShapeObject {
    public float degree;
    public float speed = 1;

    public Stone(float x, float y) {
        super(x, y);
        setPentagon(5);
        setColor(Color.RED);

        long seed = System.currentTimeMillis();
        int bound = 3600;
        Random rand = new Random(seed);
        degree = (float) ((rand.nextInt(bound)) * 0.1);
    }

    public void update() {
        this.x = (float) (this.x + speed * Math.cos(Math.toRadians(degree)));
        this.y = (float) (this.y + speed * Math.sin(Math.toRadians(degree)));
    }
}
