package com.yhnil.invisible.framework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.yhnil.invisible.framework.main.GameScene;
import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.main.UiBridge;
import com.yhnil.invisible.framework.res.sound.SoundEffects;
import com.yhnil.invisible.framework.res.sound.SoundMusic;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    private static final int FPS_SECONDS = 10;
    private GameTimer timer;
    private int frameCount;
    public static SoundMusic soundMusic = new SoundMusic();
    public GameView(Context context) {
        super(context);
        init();
    }

    private void init() {
        UiBridge.setView(this);
        timer = new GameTimer(FPS_SECONDS, 1);
        SoundEffects.get().loadAll(getContext());
        soundMusic.getContext(getContext());
        postFrameCallback();

    }

    public MediaPlayer getSoundMusic() {
        return soundMusic.mediaPlayer;
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
            frameCount = 0;
            timer.reset();
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return GameScene.getTop().onTouchEvent(event);
    }

    public void onDestory() {
        while(GameScene.getTop() != null)
            GameScene.pop();
    }
}
