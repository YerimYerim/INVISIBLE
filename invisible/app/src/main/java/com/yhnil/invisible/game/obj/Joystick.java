package com.yhnil.invisible.game.obj;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.yhnil.invisible.R;
import com.yhnil.invisible.framework.iface.Touchable;
import com.yhnil.invisible.framework.main.GameObject;
import com.yhnil.invisible.framework.res.bitmap.SharedBitmap;


public class Joystick extends GameObject implements Touchable {

    private final SharedBitmap sbmp;
    private float x;
    private float y;
    private final int size;
    private final Direction direction;
    private boolean down;
    private float xDown, yDown;
    private float dx, dy;
    private double angle;

    public enum Direction {
        normal, horizontal, vertical
    }
    public Joystick(float x, float y, Direction dir, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.direction = dir;
        this.sbmp = SharedBitmap.load(R.mipmap.joystick);
        this.down = false;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        sbmp.draw(canvas, x, y);
        if(down){
            sbmp.draw(canvas, x + dx, y + dy);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                this.x = event.getX();
                this.y = event.getY();
                dx = dy = 0;
                down = true;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d( "action move" , " " + down);
                if (!down) {
                    return false;
                }
                float dx = event.getX() - this.x;
                float dy = event.getY() - this.y;
                move(dx, dy);
                break;
            case MotionEvent.ACTION_UP:
                Log.d( "ACTION_UP" , " " + down);
                down = false;
        }
        return false;
    }

    private void move(float dx, float dy) {
        if (direction == Direction.vertical) {
            dx = 0;
            if (dy < -size) {
                dy = -size;
            } else if (dy > size) {
                dy = size;
            }
        } else if (direction == Direction.horizontal) {
            dy = 0;
            if (dx < -size) {
                dx = -size;
            } else if (dx > size) {
                dx = size;
            }
        } else {
            float dist = (float) Math.sqrt(dx * dx + dy * dy);
            if (dist > size) {
                dx = dx * size / dist;
                dy = dy * size / dist;
            }
        }
        this.dx = dx;
        this.dy = dy;
        this.angle = Math.atan2(dy, dx);
    }

    public int getHorzDirection() {
        if (!down) return 0;
        if (dx == 0) return 0;
        return angle < Math.PI / 2 && angle > -Math.PI / 2 ? 1 : -1;
    }
}
