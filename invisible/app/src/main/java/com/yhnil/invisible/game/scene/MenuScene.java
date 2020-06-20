package com.yhnil.invisible.game.scene;

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

    private void initObjects() {
        mediaPlayer = GameView.soundMusic.play(manu);
        mediaPlayer.start();

        timer = new GameTimer(255, (int) (255/2.0f));
        int cx = UiBridge.metrics.center.x + UiBridge.metrics.center.x /2;
        int y = UiBridge.metrics.size.y - UiBridge.y(100);
        Button button = new Button(cx, y, R.mipmap.start18, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        button.setOnClickRunnable(new Runnable() {
            @Override
            public void run() {
                GameScene scene = new GameScene();
                pop();
                scene.push();
            }
        });
        int logox = UiBridge.metrics.center.x;
        int y2 = UiBridge.metrics.size.y - UiBridge.y(350);
        Logo = new BitmapObject(logox,y2, 1000, 400, R.mipmap.logo);
        gameWorld.add(Layer.ui.ordinal(),Logo);
        gameWorld.add(Layer.ui.ordinal(), button);
    }
}
