package com.yhnil.invisible.game.obj.sobj;

import android.graphics.Color;

import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.obj.ShapeObject;

import java.util.Random;

public class Core extends ShapeObject {
    Random random = new Random();

    float degree = 0;
    float dps; // rotate degree per second

    private DangerZone dangerZone = null;
    private CoreStone coreStones[] = new CoreStone[6];
    private int dangerZoneIndex;

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
        // degree update
        float dt = GameTimer.getTimeDiffSeconds();
        degree += (dps * dt) % 360;
        setDegree(degree);

        // coreStone update
        int index = 0;
        for(CoreStone coreStone : coreStones)
            if(coreStone != null) {
                coreStone.setDegree(degree + 60 * index++);
            }

        // dangerZone update
        if(dangerZone != null) {
            if(dangerZone.lightState == DangerZone.LightState.None) {
                dangerZoneIndex = random.nextInt(6);
                dangerZone.setColor(coreStones[dangerZoneIndex].getColor());
                dangerZone.turnOn();
            }
            dangerZone.setDegree(degree + 60 * dangerZoneIndex);
        }
    }
}
