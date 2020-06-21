package com.yhnil.invisible.game.scene;

import android.graphics.Point;
import android.media.MediaPlayer;

import com.yhnil.invisible.R;
import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.main.UiBridge;
import com.yhnil.invisible.framework.obj.BitmapObject;
import com.yhnil.invisible.framework.obj.ScoreObject;
import com.yhnil.invisible.framework.obj.ui.Button;
import com.yhnil.invisible.framework.res.sound.SoundMusic;
import com.yhnil.invisible.framework.view.GameView;

import static com.yhnil.invisible.R.raw.manu;

public class MenuScene extends com.yhnil.invisible.framework.main.GameScene {
    public MediaPlayer mediaPlayer;
    private int soundPosition;

    public enum Layer {
        ui, COUNT
    }
    enum State { First, Increase, Decrease, Coount};
    State state = State.First;

    private GameTimer timer = null;
    BitmapObject Logo;
    @Override
    protected int getLayerCount() {
        return Layer.COUNT.ordinal();
    }

    @Override
    public void update() {
        super.update();
        int alpha = Math.min(timer.getRawIndex(), 255);
        switch (state){
            case First:
                if (timer.done())
                    state = State.Decrease;
                break;
            case Increase:
                alpha = 127 + alpha / 2;
                if (timer.done())
                    state = State.Decrease;
                break;
            case Decrease:
                alpha = 255 - alpha / 2;
                if (timer.done())
                    state = State.Increase;
                break;
        }
        Logo.setPaintAlpha(alpha);

        if (timer.done())
            timer.reset();
    }

    @Override
    public void enter() {
        super.enter();
        initObjects();
    }
    @Override
    public void exit() {
        mediaPlayer.release();
        super.exit();
    }

    @Override
    public void onPause() {
        mediaPlayer.pause();
        soundPosition = mediaPlayer.getCurrentPosition();
        super.onPause();
    }

    @Override
    public void onResume() {
        mediaPlayer.seekTo(soundPosition);
        mediaPlayer.start();
        super.onResume();
    }

    private void initObjects() {
        // sound
        mediaPlayer = GameView.soundMusic.play(manu);
        mediaPlayer.start();

        // timer
        timer = new GameTimer(255, (int) (255/2.0f));

        Point startPos = new Point();
        startPos.x = UiBridge.metrics.center.x - UiBridge.x(50);
        startPos.y = UiBridge.metrics.center.y + UiBridge.y(150);
        Button button_start = new Button(startPos.x, startPos.y, 500, 500,
                R.mipmap.start, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        button_start.setOnClickRunnable(new Runnable() {
            @Override
            public void run() {
                GameScene scene = new GameScene();
                pop();
                scene.push();
            }
        });
        Point exitPos = new Point();
        exitPos.x = startPos.x * 2;
        exitPos.y = startPos.y + UiBridge.y(100);
        Button button_exit = new Button(exitPos.x, exitPos.y, 450, 150,
                R.mipmap.exit, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        button_exit.setOnClickRunnable(new Runnable() {
            @Override
            public void run() {
                GameScene scene = new GameScene();
                pop();
                scene.push();
            }
        });

        Point logoPos = new Point();
        logoPos.x = UiBridge.metrics.center.x;
        logoPos.y = UiBridge.metrics.center.y - UiBridge.y(100);
        Logo = new BitmapObject(logoPos.x, logoPos.y, 1000, 400, R.mipmap.logo);
        gameWorld.add(Layer.ui.ordinal(), Logo);
        gameWorld.add(Layer.ui.ordinal(), button_start);
        gameWorld.add(Layer.ui.ordinal(), button_exit);
    }
}
