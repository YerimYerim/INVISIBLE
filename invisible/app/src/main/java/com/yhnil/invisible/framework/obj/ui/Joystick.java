package com.yhnil.invisible.framework.obj.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
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
    private boolean down;
    private Vector Down;
    private Vector dPos;
    private double angle;
    private Runnable onClickRunnable;
    private Paint paint;

    public Joystick() {
        this.pos= new Vector(0.0f, 0.0f);
        this.dPos = new Vector(0.0f , 0.0f);
        this.Down = new Vector(0.0f , 0.0f);
        this.back = SharedBitmap.load(R.mipmap.joystickback);
        this.center = SharedBitmap.load(R.mipmap.joystickcenter);
        this.down = false;
        this.size = back.getWidth()/2  - center.getWidth()/2;
        this.paint = new Paint();
        this.paint.setAlpha((int) (255*0.5));
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        if(down){
            back.draw(canvas, pos.x  - back.getWidth()/2 , pos.y  - back.getWidth()/2, paint );
            center.draw(canvas, pos.x - center.getWidth() / 2+ dPos.x , pos.y + dPos.y -  center.getWidth()/2, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Down.y = event.getY();
                Down.x = event.getX();
                pos = Down;
                dPos.x = dPos.y = 0.0f;
                down = true;
                return true;
            case MotionEvent.ACTION_MOVE:
                if (!down) {
                    return false;
                }
                Vector tempDPos = new Vector (event.getX() - Down.x , event.getY() -  Down.y);
                move(tempDPos);
                break;
            case MotionEvent.ACTION_UP:
                down = false;
                dPos.x = 0;
                dPos.y = 0;
                return false;
        }
        return false;
    }

    private void move(Vector P) {
         float dist = (float) Math.sqrt(P.x * P.x + P.y * P.y);
         if (dist > size) {
             P.x = P.x * size / dist;
             P.y = P.y * size / dist;
         }
         this.dPos = P;
        this.angle = Math.atan2(dPos.y, dPos.x);
    }

    public Vector getDirection() {
        Vector distVector = new Vector( dPos.x  , dPos.y );
        if (!down) {}
        else {
            double Length = getLength();
            distVector.x = (float) ((Math.cos(angle)) * getLength());
            distVector.y = (float) ((Math.sin(angle)) * getLength());
        }
        return distVector;
    }

    public double getLength(){
        Vector distVector = new Vector( dPos.x  , dPos.y );
        float dist = (float) Math.sqrt(distVector.x * distVector.x + distVector.y * distVector.y);
        return dist / size;
    }

    public void setOnClickRunnable(Runnable runnable) {
        this.onClickRunnable = runnable;
    }
}
