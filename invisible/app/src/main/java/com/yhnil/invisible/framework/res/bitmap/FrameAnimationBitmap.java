package com.yhnil.invisible.framework.res.bitmap;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.yhnil.invisible.framework.main.GameTimer;

public class FrameAnimationBitmap {

    private static final String TAG = FrameAnimationBitmap.class.getSimpleName();
    private SharedBitmap sbmp;
    private int frameWidth;
    private int frames;
    private int fps;
    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();
    private GameTimer timer;


    public FrameAnimationBitmap(int resId, int framesPerSecond, int frameCount) {
        this.sbmp = SharedBitmap.load(resId);
        this.fps = framesPerSecond;
        if (frameCount == 0) {
            this.frameWidth = sbmp.getHeight();
            frameCount = sbmp.getWidth() / this.frameWidth;
        } else {
            this.frameWidth = sbmp.getWidth() / frameCount;
        }
        this.frames = frameCount;
        this.timer = new GameTimer(frames, framesPerSecond);
        srcRect.top = 0;
        srcRect.bottom = sbmp.getHeight();
    }

    public void draw(Canvas canvas, float x, float y) {
        int halfWidth = frameWidth / 2;
        int halfHeight = sbmp.getHeight() / 2;
        dstRect.left = x - halfWidth;
        dstRect.top = y - halfHeight;
        dstRect.right = x + halfWidth;
        dstRect.bottom = y + halfHeight;
        draw(canvas, dstRect, null);
    }
    public void draw(Canvas canvas, RectF rect, Paint paint) {
        int index = timer.getIndex();
        srcRect.left = frameWidth * index;
        srcRect.right = srcRect.left + frameWidth;

        canvas.drawBitmap(sbmp.getBitmap(), srcRect, rect, paint);
    }
    public void setBitmapResource(int resId) {
        sbmp = SharedBitmap.load(resId);
    }

    public void reset() {
        timer.reset();
    }

    public boolean done() {
        return timer.done();
    }

    public void draw(Canvas canvas, Matrix matrix) {
        canvas.drawBitmap(sbmp.getBitmap(), matrix, null);
    }

    public int getWidth() {
        return frameWidth;
    }
    public int getHeight() {
        return sbmp.getHeight();
    }

    public void draw(Canvas canvas, float x, float y, int radius) {
        dstRect.left = x - radius;
        dstRect.top = y - radius;
        dstRect.right = x + radius;
        dstRect.bottom = y + radius;
        draw(canvas, dstRect, null);
    }
    public void draw(Canvas canvas, float x, float y, int xRadius, int yRadius) {
        dstRect.left = x - xRadius;
        dstRect.top = y - yRadius;
        dstRect.right = x + xRadius;
        dstRect.bottom = y + yRadius;
        draw(canvas, dstRect, null);
    }
}
