package com.yhnil.invisible.framework.obj;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.yhnil.invisible.framework.iface.BoxCollidable;
import com.yhnil.invisible.framework.main.GameObject;
import com.yhnil.invisible.framework.main.UiBridge;
import com.yhnil.invisible.framework.res.bitmap.SharedBitmap;

public class BitmapObject extends GameObject implements BoxCollidable {
    private static final String TAG = BitmapObject.class.getSimpleName();
    protected SharedBitmap sbmp;
    protected final RectF dstRect;
    protected int width;
    protected int height;
    public Paint paint;

    public BitmapObject(float x, float y, int width, int height, int resId) {
        paint = new Paint();
        sbmp = SharedBitmap.load(resId);
        this.x = x;
        this.y = y;
        this.dstRect = new RectF();
        if (width == 0) {
            width = UiBridge.x(sbmp.getWidth());
        } else if (width < 0) {
            width = UiBridge.x(sbmp.getWidth()) * -width / 100;
        }
        this.width = width;
        if (height == 0) {
            height = UiBridge.x(sbmp.getHeight());
        } else if (height < 0) {
            height = UiBridge.x(sbmp.getHeight()) * -height / 100;
        }
        this.height = height;
    }

    @Override
    public float getRadius() {
        return this.width / 2;
    }

    public void draw(Canvas canvas) {
        int halfWidth = width / 2;
        int halfHeight = height / 2;
        dstRect.left = x - halfWidth;
        dstRect.top = y - halfHeight;
        dstRect.right = x + halfWidth;
        dstRect.bottom = y + halfHeight;
        canvas.drawBitmap(sbmp.getBitmap(), null, dstRect, paint);
    }
    public void setPaintAlpha(int x){
        paint.setAlpha(x);
    }
    @Override
    public void getBox(RectF rect) {
        int hw = width / 2;
        int hh = height / 2;
        rect.left = x - hw;
        rect.top = y - hh;
        rect.right = x + hw;
        rect.bottom = y + hh;
    }
}
