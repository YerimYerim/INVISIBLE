package com.yhnil.invisible.framework.obj.bg;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.yhnil.invisible.framework.main.GameObject;
import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.main.UiBridge;
import com.yhnil.invisible.framework.res.bitmap.SharedBitmap;

public class ImageScrollBackground extends GameObject {
    private final SharedBitmap sbmp;
//    private final int pageSize;
    private int speed;
    private float scrollX;
    private float scrollY;
    private boolean horizontal;
    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();

    public enum Orientation { horizontal, vertical };
    public ImageScrollBackground(int resId, Orientation orientation, int speed) {
        this.sbmp = SharedBitmap.load(resId);
        this.horizontal = orientation == Orientation.horizontal;
        this.speed = speed;
        srcRect.set(0, 0, sbmp.getWidth(), sbmp.getHeight());
    }

    @Override
    public void update() {
        if (speed == 0) return;
        float amount = speed * GameTimer.getTimeDiffSeconds();
        if (horizontal) {
            scrollX += amount;
        } else {
            scrollY += amount;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (horizontal)
            drawHorizontal(canvas);
        else
            drawVertical(canvas);
    }

    private void drawVertical(Canvas canvas) {
        int left = 0;
        int top = 0;
        int right = UiBridge.metrics.size.x;
        int bottom = UiBridge.metrics.size.y;
        int pageSize = sbmp.getHeight() * (right - left) / sbmp.getWidth();

        canvas.save();
        canvas.clipRect(left, top, right, bottom);

        float curr = scrollY % pageSize;
        if (curr > 0) curr -= pageSize;
        curr += top;
        while (curr < bottom) {
            dstRect.set(left, curr, right, curr + pageSize);
            curr += pageSize;
            canvas.drawBitmap(sbmp.getBitmap(), srcRect, dstRect, null);
        }
        canvas.restore();
    }

    private void drawHorizontal(Canvas canvas) {
        int left = 0;
        int top = 0;
        int right = UiBridge.metrics.size.x;
        int bottom = UiBridge.metrics.size.y;
        int pageSize = sbmp.getWidth() * (bottom - top) / sbmp.getHeight();

        canvas.save();
        canvas.clipRect(left, top, right, bottom);

        float curr = scrollX % pageSize;
        if (curr > 0) curr -= pageSize;
        curr += left;
        while (curr < right) {
            dstRect.set(curr, top, curr + pageSize, bottom);
            curr += pageSize;
            canvas.drawBitmap(sbmp.getBitmap(), srcRect, dstRect, null);
        }
        canvas.restore();
    }

    public void scrollTo(int x, int y) {
        this.scrollX = x;
        this.scrollY = y;
    }
}
