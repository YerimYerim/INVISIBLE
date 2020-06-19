package com.yhnil.invisible.game.obj.sobj;

import android.graphics.Color;
import android.util.Log;

import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.obj.ShapeObject;

public class DangerZone extends ShapeObject {
    private GameTimer timer;

    public float startingTime = 2;
    public float lifeTime = 2;
    public float endingTime = 2;

    public DangerZone(float x, float y) {
        super(x, y);
        setColor(Color.MAGENTA);
        setSector(100);

        timer = new GameTimer(200, 100);
    }

    public void update() {
        Log.d("count", "" + timer.getIndex());
        Log.d("rawIndex", "" + timer.getRawIndex());
    }
}
