package com.yhnil.invisible.game.scene;

import android.graphics.RectF;

import com.yhnil.invisible.R;
import com.yhnil.invisible.framework.main.GameScene;
import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.main.UiBridge;
import com.yhnil.invisible.framework.obj.ScoreObject;
import com.yhnil.invisible.framework.obj.ui.Button;

public class OverScene extends GameScene {
    private static final String TAG = OverScene.class.getSimpleName();

    public enum Layer {
        bg, enemy, player, ui, COUNT
    }

    private ScoreObject scoreObject;
    private GameTimer timer;

    @Override
    protected int getLayerCount() {
        return Layer.COUNT.ordinal();
    }

    @Override
    public void update() {
        super.update();
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
        timer = new GameTimer(2, 1);
        int cx = UiBridge.metrics.center.x;
        int y = UiBridge.metrics.size.y - UiBridge.y(100);
        Button button = new Button(cx, y, R.mipmap.btn_start_game, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        button.setOnClickRunnable(new Runnable() {
            @Override
            public void run() {
               OverScene.pop();
            }
        });
        gameWorld.add(Layer.ui.ordinal(), button);
    }
}
