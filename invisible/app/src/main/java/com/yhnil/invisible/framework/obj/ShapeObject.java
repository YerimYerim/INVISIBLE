package com.yhnil.invisible.framework.obj;

import android.graphics.Canvas;
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


    enum ShapeType{ShapeCircle, ShapePolygon, ShapeNone;};
    ShapeType shapeType;

    private int cnt;
    float[] pts;

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
    public void setPentagon(float radius){
        shapeType = ShapeType.ShapePolygon;
        cnt = 6;
        pts = new float[cnt * 2];
        for(int i = 0; i < cnt; ++i)
        {
            pts[i*2+0] = (float) Math.cos((float)i/cnt*2*Math.PI)*radius;
            pts[i*2+1] = (float) Math.sin((float)i/cnt*2*Math.PI)*radius;
        }
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

        Path path = new Path();
        for(int i = 0; i < cnt; ++i) {
            if (i == 0)
                path.moveTo(this.x + this.pts[0] / matUtil.scale + matUtil.tx,
                        (this.y + this.pts[1]) / matUtil.scale + matUtil.ty);
            else
                path.lineTo(this.x + this.pts[i * 2 + 0] / matUtil.scale + matUtil.tx,
                        (this.y + this.pts[i * 2 + 1]) / matUtil.scale + matUtil.ty);
        }
        float r = this.radius / matUtil.scale;
        switch (shapeType) {
            case ShapeCircle:
                canvas.drawCircle(x, y, r, paint);
                break;
            case ShapePolygon:
                canvas.drawPath(path, paint);
                break;
        }
    }
}
