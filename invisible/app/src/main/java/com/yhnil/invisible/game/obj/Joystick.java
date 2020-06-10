package com.yhnil.invisible.game.obj;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.yhnil.invisible.R;
import com.yhnil.invisible.framework.iface.Touchable;
import com.yhnil.invisible.framework.main.GameObject;
import com.yhnil.invisible.framework.res.bitmap.SharedBitmap;
import com.yhnil.invisible.framework.util.Vector;


public class Joystick extends GameObject implements Touchable {

    private final SharedBitmap back;
    private final SharedBitmap center;
    private Vector pos;
    private final int size;
    private final Direction direction;
    private boolean down;
    private Vector Down;
    private Vector dPos;
    private double angle;

    public enum Direction {
        normal, horizontal, vertical
    }
    public Joystick(Direction dir) {
        this.pos= new Vector(0.0f, 0.0f);
        this.dPos = new Vector(0.0f , 0.0f);
        this.Down = new Vector(0.0f , 0.0f);
        this.direction = dir;
        this.back = SharedBitmap.load(R.mipmap.joystickback);
        this.center = SharedBitmap.load(R.mipmap.joystickcenter);
        this.down = false;
        this.size = back.getWidth()/2  - center.getWidth()/2;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        if(down){
            back.draw(canvas, pos.x  - back.getWidth()/2 , pos.y  - back.getWidth()/2  );
            center.draw(canvas, pos.x - center.getWidth() / 2+ dPos.x , pos.y + dPos.y -  center.getWidth()/2);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Down.y = event.getY();
                Down.x = event.getX();
                pos = Down;
                dPos.x = dPos.y = 0.0f;
                down = true;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d( "action move" , " " + down);
                if (!down) {
                    return false;
                }
                Vector tempDPos = new Vector (event.getX() - Down.x , event.getY() -  Down.y);
                move(tempDPos);
                break;
            case MotionEvent.ACTION_UP:
                Log.d( "ACTION_UP" , " " + down);
                down = false;
        }
        return false;
    }

    private void move(Vector P) {
        if (direction == Direction.vertical) {
            P.x = 0;
            if (P.y < -size) {
                P.y = -size;
            } else if (P.y > size) {
                P.y = size;
            }
        } else if (direction == Direction.horizontal) {
            P.y = 0;
            if (P.x < -size) {
                P.x = -size;
            } else if (P.x > size) {
                P.x = size;
            }
        } else {
            float dist = (float) Math.sqrt(P.x * P.x + P.y * P.y);
            if (dist > size) {
                P.x = P.x * size / dist;
                P.y = P.y * size / dist;
            }
        }
        this.dPos = P;
        this.angle = Math.atan2(dPos.y, dPos.x);
    }

    public Vector getDirection() {
        Vector distVector = new Vector( dPos.x - pos.x , dPos.y - pos.y);
        if (!down) {}
        else
        {
            double Length = getLength();
            distVector.x = (float) ((size * Math.cos(angle)) * getLength());
            distVector.y = (float) ((size * Math.sin(angle)) * getLength());
        }
        return distVector;
    }

    public double getLength(){
        Vector distVector = new Vector( dPos.x - pos.x , dPos.y - pos.y);
        float dist = (float) Math.sqrt(distVector.x * distVector.x + distVector.y * distVector.y);
        return dist;
    }
}
