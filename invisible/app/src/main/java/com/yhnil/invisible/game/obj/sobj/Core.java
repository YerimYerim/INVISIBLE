package com.yhnil.invisible.game.obj.sobj;

import android.graphics.Color;
import android.util.Log;

import com.yhnil.invisible.framework.iface.CircleCollidable;
import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.obj.ShapeObject;
import com.yhnil.invisible.framework.util.Vector;

import java.util.Random;

public class Core extends ShapeObject implements CircleCollidable{
    Random random = new Random();

    float degree = 0;
    float dps; // rotate degree per second

    private DangerZone dangerZone = null;
    public CoreStone coreStones[] = new CoreStone[6];
    public int dangerZoneIndex;
    public int grayZoneIndex = 0;
    public GameTimer timer;
    int colors[] = {
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.CYAN,
            Color.MAGENTA,
            Color.YELLOW
    };
    public Core(float x, float y) {
        super(x, y);
        setPentagon(20);
        setColor(Color.BLUE);
        grayZoneIndex = 0;
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
        if( grayZoneIndex == 6 && timer == null)
        {
            timer = new GameTimer(10,1);
        }
        if( grayZoneIndex == 6 && timer.done()) {
            int index = 0;
            for (int color : colors) {
                coreStones[index].setColor(color);
                ++index;
            }
            timer  = null;
            grayZoneIndex= 0;
        }


        // coreStone update
        int index = 0;
        for(CoreStone coreStone : coreStones)
            if(coreStone != null) {
                coreStone.setDegree(degree + 60 * index++);
            }
        Log.d("fdfd",""+this.grayZoneIndex);
        // dangerZone update
        if(dangerZone != null && grayZoneIndex!=6 ) {
            if(dangerZone.lightState == DangerZone.LightState.None) {
                dangerZoneIndex = random.nextInt(6);
                while(coreStones[dangerZoneIndex].getColor()==Color.GRAY)
                {
                    dangerZoneIndex= (dangerZoneIndex+1)%6;
                }
                dangerZone.setColor(coreStones[dangerZoneIndex].getColor());
                dangerZone.turnOn();
            }
            dangerZone.setDegree(degree + 60 * dangerZoneIndex);
        }
    }

    @Override
    public float getCircle(Vector position) {
        position.x = x;
        position.y = y;
        return 20-2;
    }
}
