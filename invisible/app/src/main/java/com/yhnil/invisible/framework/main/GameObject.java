package com.yhnil.invisible.framework.main;

import android.graphics.Canvas;

import com.yhnil.invisible.framework.iface.Touchable;

public class GameObject {
    protected float x, y;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    public float getRadius() { return 0; }
    public void update() {}
    public void draw(Canvas canvas) {}

    public void captureTouch() {
        if (!(this instanceof Touchable)) {
            return;
        }
        GameScene.getTop().getGameWorld().captureTouch((Touchable) this);
    }
    public void releaseTouch() {
        GameScene.getTop().getGameWorld().releaseTouch();
    }
    public void move(float dx, float dy) {
        x += dx;
        y += dy;
    }
    public void remove() {
        GameWorld gw = GameScene.getTop().getGameWorld();
        gw.removeObject(this);
    }
}
