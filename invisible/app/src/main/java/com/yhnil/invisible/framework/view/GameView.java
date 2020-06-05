package com.yhnil.invisible.framework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.yhnil.invisible.framework.main.GameScene;
import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.main.UiBridge;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    private static final int FPS_SECONDS = 10;
    private GameTimer timer;
    private int frameCount;

    public GameView(Context context) {
        super(context);
        init();
    }

    private void init() {
        UiBridge.setView(this);
        timer = new GameTimer(FPS_SECONDS, 1);
        postFrameCallback();
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void postFrameCallback() {
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long frameTimeNanos) {
                GameTimer.setCurrentTimeNanos(frameTimeNanos);
                update();
                invalidate();
                postFrameCallback();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        GameScene.getTop().draw(canvas);
    }

    public void update() {
        GameScene.getTop().update();
        frameCount++;
        if (timer.done()) {
            Log.d(TAG, "Frame Count /s = " + ((float) frameCount / FPS_SECONDS));// + " Total Objects = " + GameScene.get().getAllObjectCount());
            frameCount = 0;
            timer.reset();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return GameScene.getTop().onTouchEvent(event);
    }
}
