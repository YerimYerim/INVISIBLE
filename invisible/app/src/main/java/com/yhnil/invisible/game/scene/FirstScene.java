package com.yhnil.invisible.game.scene;

import android.graphics.RectF;

import com.yhnil.invisible.R;
import com.yhnil.invisible.framework.main.GameScene;
import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.main.GameWorld;
import com.yhnil.invisible.framework.main.UiBridge;
import com.yhnil.invisible.framework.obj.BitmapObject;
import com.yhnil.invisible.framework.obj.ScoreObject;
import com.yhnil.invisible.framework.obj.ui.Button;
import com.yhnil.invisible.game.obj.Ball;
import com.yhnil.invisible.game.obj.CityBackground;
import com.yhnil.invisible.game.obj.Core;
import com.yhnil.invisible.game.obj.PlayGround;
import com.yhnil.invisible.game.obj.Stone;

import java.util.Random;

public class FirstScene extends GameScene {
    private static final String TAG = FirstScene.class.getSimpleName();

    public enum Layer {
        bg, enemy, player, ui, COUNT
    }

    private Ball ball;
    private ScoreObject scoreObject;
    private GameTimer timer;

    @Override
    protected int getLayerCount() {
        return Layer.COUNT.ordinal();
    }

    @Override
    public void update() {
        super.update();
//        Log.d(TAG, "Score: " + timer.getRawIndex());
        if (timer.done()) {
            scoreObject.add(100);
            timer.reset();
        }
    }

    @Override
    public void enter() {
        super.enter();
        initObjects();
    }

    private void initObjects() {
        gameWorld.add(Layer.enemy.ordinal(), new PlayGround(0, 0));
        gameWorld.add(Layer.enemy.ordinal(), new Core(0, 0));
        gameWorld.add(Layer.enemy.ordinal(), new Stone(0, 0));

        RectF rbox = new RectF(UiBridge.x(-52), UiBridge.y(20), UiBridge.x(-20), UiBridge.y(62));
        scoreObject = new ScoreObject(R.mipmap.number_64x84, rbox);
        gameWorld.add(Layer.ui.ordinal(), scoreObject);
        timer = new GameTimer(2, 1);

        int cx = UiBridge.metrics.center.x;
        int y = UiBridge.metrics.size.y - UiBridge.y(100);
        Button button = new Button(cx, y, R.mipmap.btn_start_game, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        button.setOnClickRunnable(new Runnable() {
            @Override
            public void run() {
                SecondScene scene = new SecondScene();
                scene.push();
            }
        });
        gameWorld.add(Layer.ui.ordinal(), button);
    }
}
