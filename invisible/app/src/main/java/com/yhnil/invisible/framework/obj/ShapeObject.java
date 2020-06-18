package com.yhnil.invisible.framework.obj;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import com.yhnil.invisible.framework.main.GameObject;
import com.yhnil.invisible.framework.main.UiBridge;

public class ShapeObject extends GameObject {
    public class MatUtil{
        public float scale = 1.0f;
        public float tx = 0.0f, ty = 0.0f;
    }
    MatUtil matUtil;

    enum ShapeType{ShapeCircle, ShapePolygon, ShapeSector, ShapeNone;};
    ShapeType shapeType;

    private int cnt;
    float[] pts;

    Paint paint;
    float degree;
    float radius = 0;

    @Override
    public float getRadius() {
        return radius;
    }
    public int getColor() {
        return paint.getColor();
    }
    public float getDegree() {
        return degree;
    }

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
    public void setPentagon(float radius) {
        shapeType = ShapeType.ShapePolygon;
        cnt = 6;
        pts = new float[cnt * 2];
        for(int i = 0; i < cnt; ++i)
        {
            pts[i*2+0] = (float) Math.cos((float)(i+0.5)/cnt*2*Math.PI)*radius;
            pts[i*2+1] = (float) Math.sin((float)(i+0.5)/cnt*2*Math.PI)*radius;
        }
    }
    public void setPentagonUnit(float radius) {
        shapeType = ShapeType.ShapePolygon;
        cnt = 3;
        pts = new float[cnt * 2];
        pts[0] = 0;
        pts[1] = 0;
        pts[2] = (float) Math.cos(Math.PI / +6.0f)*radius;
        pts[3] = (float) Math.sin(Math.PI / +6.0f)*radius;
        pts[4] = (float) Math.cos(Math.PI / -6.0f)*radius;
        pts[5] = (float) Math.sin(Math.PI / -6.0f)*radius;
    }
    public void setSector(float radius) {
        shapeType = ShapeType.ShapeSector;
        this.radius = radius;
        paint.setColor(Color.YELLOW);
    }

    public void setColor(int color){
        paint.setColor(color);
    }
    public void setDegree(float degree){
        this.degree = degree;
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

        Path path = new Path();
        for(int i = 0; i < cnt; ++i) {
            float tx = (float) (this.pts[i * 2 + 0] * Math.cos(Math.toRadians(degree)) -
                                this.pts[i * 2 + 1] * Math.sin(Math.toRadians(degree)));
            float ty = (float) (this.pts[i * 2 + 0] * Math.sin(Math.toRadians(degree)) +
                                this.pts[i * 2 + 1] * Math.cos(Math.toRadians(degree)));
            if (i == 0) {
                path.moveTo((this.x + tx) / matUtil.scale + matUtil.tx,
                        (this.y + ty) / matUtil.scale + matUtil.ty);
            }
            else {
                path.lineTo((this.x + tx) / matUtil.scale + matUtil.tx,
                        (this.y + ty) / matUtil.scale + matUtil.ty);
            }
        }
        switch (shapeType) {
            case ShapeCircle:
                canvas.drawCircle(x, y, r, paint);
                break;
            case ShapePolygon:
                canvas.drawCircle(x, y, r, paint);
                canvas.drawPath(path, paint);
                break;
            case ShapeSector:
                canvas.drawArc(x - r, y-r, x+r, y+r,
                        degree - 30,60,true, paint);
                break;
        }
    }
}
