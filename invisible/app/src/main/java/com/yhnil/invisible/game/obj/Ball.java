package com.yhnil.invisible.game.obj;

import com.yhnil.invisible.R;
import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.main.UiBridge;
import com.yhnil.invisible.framework.obj.AnimObject;

public class Ball extends AnimObject {
    protected float dx, dy;
    public Ball(float x, float y, float dx, float dy) {
        super(x, y, 0, 0, R.mipmap.fireball_128_24f, 10, 0);
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public float getRadius() {
        return this.width / 4;
    }

    public void update() {
        float seconds = GameTimer.getTimeDiffSeconds();
        x += dx * seconds;
        float radius = getRadius();
        int screenWidth = UiBridge.metrics.size.x;
        int screenHeight = UiBridge.metrics.size.y;
//        Log.d(TAG, "dx=" + dx + " nanos=" + nanos + " x=" + x);
        if (dx > 0 && x > screenWidth - radius) {
            dx *= -1;
            x = screenWidth - radius;
        }
        if (dx < 0 && x < radius) {
            dx *= -1;
            x = radius;
        }
        y += dy * seconds;
        if (dy > 0 && y > screenHeight - radius) {
            dy *= -1;
            y = screenHeight - radius;
        }
        if (dy < 0 && y < radius) {
            dy *= -1;
            y = radius;
        }
    }
}
