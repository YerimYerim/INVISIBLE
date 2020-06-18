package com.yhnil.invisible.game.obj.sobj;

import android.graphics.Color;

import com.yhnil.invisible.framework.iface.CircleCollidable;
import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.obj.ShapeObject;
import com.yhnil.invisible.framework.util.Vector;

public class Core extends ShapeObject implements CircleCollidable {
    float degree = 0;
    float dps; // rotate degree per second

    private DangerZone dangerZone = null;
    private CoreStone coreStones[] = new CoreStone[6];

    public Core(float x, float y) {
        super(x, y);
        setPentagon(20);
        setColor(Color.BLUE);

        dps = 45;
    }

    public void connectDangerZone(DangerZone dangerZone) {
        this.dangerZone = dangerZone;
    }

    public void connectCoreStone(CoreStone coreStone, int index) {
        this.coreStones[index] = coreStone;
    }

    public void update() {
        float dt = GameTimer.getTimeDiffSeconds();
        degree += (dps * dt) % 360;

        setDegree(degree);
        if(dangerZone != null)
            dangerZone.setDegree(degree);
        int index = 0;
        for(CoreStone coreStone : coreStones)
            if(coreStone != null)
                coreStone.setDegree(degree + 60 * index++);
    }

    @Override
    public float getCircle(Vector position) {
        position.x = x;
        position.y = y;

        return 20-2;
    }
}
