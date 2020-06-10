package com.yhnil.invisible.framework.obj;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.yhnil.invisible.framework.main.GameObject;
import com.yhnil.invisible.framework.main.UiBridge;

public class ShapeObject extends GameObject {
    public class MatUtil{
        public float scale = 1.0f;
        public float tx = 0.0f, ty = 0.0f;
    }
    MatUtil matUtil;

    enum ShapeType{ShapeCircle, ShapeNone};
    ShapeType shapeType;

    Paint paint;
    float radius;

    public ShapeObject(float x, float y) {
        this.x = x;
        this.y = y;
        matUtil = new MatUtil();
        paint = new Paint();

        setMatrix();
    }

    public void setCircle(float radius) {
        shapeType = ShapeType.ShapeCircle;
        this.radius = radius;
    }
    public void setColor(int color){
        paint.setColor(color);
    }
    public void setMatrix() {
        float scale = 200.0f / UiBridge.metrics.size.x;
        matUtil.scale = scale;
        matUtil.tx = UiBridge.metrics.center.x;
        matUtil.ty = UiBridge.metrics.center.y;
    }

    public void draw(Canvas canvas) {
        float x = this.x / matUtil.scale + matUtil.tx;
        float y = this.y / matUtil.scale + matUtil.ty;
        float r = this.radius / matUtil.scale;
        switch (shapeType) {
            case ShapeCircle:
                canvas.drawCircle(x, y, r, paint);
                break;
        }
    }
}
