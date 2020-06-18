package com.yhnil.invisible.game.obj.sobj;

import android.graphics.Color;
import android.util.Log;

import com.yhnil.invisible.framework.obj.ShapeObject;

import java.util.Random;

import static com.yhnil.invisible.game.obj.sobj.State.begin;

public class Stone extends ShapeObject {
    public float degree;
    public float speed = 0.5f;
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
        Log.d("" ,"스톤 생성" );
        long seed = System.currentTimeMillis();
        Random rand = new Random(seed);
        setCircle(5);

        int colorindex = 6;
        setColor(color[rand.nextInt(colorindex)]);
        int bound = 3600;
        int timebound = 20;
        degree = (float) ((rand.nextInt(bound)) * 0.1);
        starttime = rand.nextInt(timebound);
    }
    boolean IsFarDistFromCenter(){
        float distX = this.x;
        float distY = this.y;
        float dist = (float) Math.sqrt(distX * distX + distY * distY );
        Log.d("", "x "+x+ "y"+y);
        if (dist > MaxDistance)
            return true;
        else
            return false;
    }
    public void update() {
        this.x = (float) (this.x + speed * Math.cos(Math.toRadians(degree)));
        this.y = (float) (this.y + speed * Math.sin(Math.toRadians(degree)));
        if(IsFarDistFromCenter())
        {
            Log.d("", "리무브됨");
            this.remove();
            // 여기서 없애주면 됩니다.
        }
    }
}
