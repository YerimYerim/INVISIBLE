package com.yhnil.invisible.framework.obj;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.yhnil.invisible.framework.main.GameObject;

public class ShapeObject extends GameObject {
    enum ShapeType{ShapeCircle, ShapeNone};
    ShapeType shapeType;

    Paint paint;
    float radius;

    public ShapeObject(float x, float y) {
        this.x = x;
        this.y = y;
        paint = new Paint();
    }

    public void setCircle(float radius) {
        shapeType = ShapeType.ShapeCircle;
        this.radius = radius;
    }
    public void setColor(int color){
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        switch (shapeType) {
            case ShapeCircle:
                canvas.drawCircle(x, y, radius, paint);
                break;
        }
    }
}
