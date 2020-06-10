package com.yhnil.invisible.game.obj.sobj;

import android.graphics.Color;

import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.obj.ShapeObject;

public class Core extends ShapeObject {
    float degree = 0;
    float dps; // rotate degree per second

    private DangerZone dangerZone = null;

    public Core(float x, float y) {
        super(x, y);
        setPentagon(10);
        setColor(Color.BLUE);

        dps = 45;
    }

    public void connectDangerZone(DangerZone dangerZone)
    {
        this.dangerZone = dangerZone;
    }

    public void update() {
        if(dangerZone != null)
            dangerZone.setDegree(degree);
        float dt = GameTimer.getTimeDiffSeconds();
        degree += dps * dt;
        setDegree(degree);
    }
}
