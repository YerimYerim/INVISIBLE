package com.yhnil.invisible.game.obj.sobj;

import android.graphics.Color;

import com.yhnil.invisible.framework.obj.ShapeObject;

import java.util.Random;

import static com.yhnil.invisible.game.obj.sobj.State.begin;
import static com.yhnil.invisible.game.obj.sobj.State.moving;

public class Stone extends ShapeObject {
    public float degree;
    public float speed = 1;
    public float timecount = 0;
    public float starttime;
    public State state = begin;
    public Stone(float x, float y) {
        super(x, y);
        setPentagon(5);
        setColor(Color.RED);

        long seed = System.currentTimeMillis();
        int bound = 3600;
        int timebound = 20;
        Random rand = new Random(seed);
        degree = (float) ((rand.nextInt(bound)) * 0.1);
        starttime = rand.nextInt(timebound);
    }

    public void update() {
        if(state ==  moving)
        {
            this.x = (float) (this.x + speed * Math.cos(Math.toRadians(degree)));
            this.y = (float) (this.y + speed * Math.sin(Math.toRadians(degree)));
        }
        else if(state == begin) {
            timecount += 1;
            if (timecount == starttime) {
                state = moving;
            }
        }

    }
}
