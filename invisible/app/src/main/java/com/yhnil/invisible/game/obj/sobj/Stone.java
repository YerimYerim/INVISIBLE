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
    float MaxDistance  = 100;
    int[] color = {
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.CYAN,
            Color.MAGENTA,
            Color.YELLOW
    };
    public Stone(float x, float y) {
        super(x, y);
        long seed = System.currentTimeMillis();
        Random rand = new Random(seed);
        setPentagon(5);

        int colorindex = 6;
        setColor(color[rand.nextInt(colorindex)]);
        int bound = 3600;
        int timebound = 20;
        degree = (float) ((rand.nextInt(bound)) * 0.1);
        starttime = rand.nextInt(timebound);
    }
    boolean getDistFromCenter(){
        float dist = (float) Math.sqrt(this.getX() * this.getX() + this.getY() * this.getY() );
        if (dist > MaxDistance - this.getRadius())
            return false;
        else
            return true;
    }
    public void update() {
        this.x = (float) (this.x + speed * Math.cos(Math.toRadians(degree)));
        this.y = (float) (this.y + speed * Math.sin(Math.toRadians(degree)));
        if(getDistFromCenter())
        {
            // 여기서 없애주면 됩니다.
        }
    }
}
