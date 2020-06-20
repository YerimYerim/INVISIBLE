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

public class IntroScene extends com.yhnil.invisible.framework.main.GameScene {
    public MediaPlayer mediaPlayer;

    public enum Layer {
        ui, COUNT
    }
    enum State { First, Second, Count };
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
        if(timer.getRawIndex() < 255)
            Logo.setPaintAlpha(Math.min(timer.getIndex(), 255));
        if (timer.done()){
            timer.reset();
            if(state == State.First)
                state = State.Second;
            else{
                MenuScene scene = new MenuScene();
                scene.push();
            }
        }
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
        timer = new GameTimer(255, (int) (255/3.0f));

        Logo = new BitmapObject(UiBridge.metrics.center.x, UiBridge.metrics.center.y, 1000, 400, R.mipmap.logo);

        gameWorld.add(Layer.ui.ordinal(),Logo);
    }
}
