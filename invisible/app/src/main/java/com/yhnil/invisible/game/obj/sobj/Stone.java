package com.yhnil.invisible.game.obj.sobj;

import android.graphics.Color;
import android.util.Log;

import com.yhnil.invisible.framework.iface.CircleCollidable;
import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.obj.ShapeObject;
import com.yhnil.invisible.framework.util.Vector;

import java.util.Random;

public class Stone extends ShapeObject implements CircleCollidable {
    public float degree;
    public float speed = 10f;

    public enum StoneState { Normal, Contain, Count}
    public StoneState state = StoneState.Normal;
    int containIndex = 0;

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
        Log.d("" ,"스톤 생성" );
        long seed = System.currentTimeMillis();
        Random rand = new Random(seed);

        setPentagon(5);
        int colorindex = 6;
        setColor(color[rand.nextInt(colorindex)]);

        degree = (float) ((rand.nextInt(3600)) * 0.1);
    }
    boolean IsFarDistFromCenter(){
        float distX = this.x;
        float distY = this.y;
        float dist = (float) Math.sqrt(distX * distX + distY * distY );
        if (dist > MaxDistance - 5)
            return true;
        else
            return false;
    }
    public void update() {
        if(state == StoneState.Normal) {
            this.x += speed * GameTimer.getTimeDiffSeconds() * Math.cos(Math.toRadians(degree));
            this.y += speed * GameTimer.getTimeDiffSeconds() * Math.sin(Math.toRadians(degree));
            if (IsFarDistFromCenter()) {
                this.remove();
            }
        }
    }

    @Override
    public float getCircle(Vector position) {
        position.x = x;
        position.y = y;
        return getRadius();
    }
}
