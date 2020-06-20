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

public class ManuScene extends com.yhnil.invisible.framework.main.GameScene {
    private static final String TAG = ManuScene.class.getSimpleName();
    public MediaPlayer mediaPlayer;
    private SoundMusic soundMusic;

    // private MediaPlayer mp1 = MediaPlayer.create(g, manu);

    public enum Layer {
        bg, enemy, player, ui, COUNT
    }

    private ScoreObject scoreObject;
    private GameTimer timer = null;
    private  int x;
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
        x = 0;
        timer = new GameTimer(255, (int) (255/5.0f));
        int cx = UiBridge.metrics.center.x + UiBridge.metrics.center.x /2;
        int y = UiBridge.metrics.size.y - UiBridge.y(100);
        Button button = new Button(cx, y, R.mipmap.start18, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        button.setOnClickRunnable(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.release();
                GameScene scene = new GameScene();
                scene.push();
            }
        });
        int logox = UiBridge.metrics.center.x;
        int y2 = UiBridge.metrics.size.y - UiBridge.y(500);
        Logo = new BitmapObject(logox,y2, 1000, 400, R.mipmap.logo);
        Logo.setPaintAlpha(x);
        gameWorld.add(Layer.ui.ordinal(),Logo);
        gameWorld.add(Layer.ui.ordinal(), button);
    }
}
