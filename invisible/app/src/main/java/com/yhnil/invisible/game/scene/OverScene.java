package com.yhnil.invisible.game.scene;

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
    private GameTimer timer;

    @Override
    protected int getLayerCount() {
        return Layer.COUNT.ordinal();
    }

    @Override
    public void update() {
        super.update();
        if (timer.done()) {
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
        int y2 = UiBridge.metrics.size.y - UiBridge.y(300);
        Button button = new Button(cx, y, R.mipmap.start, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        Button Manu = new Button(cx, y2, R.mipmap.manu, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        button.setOnClickRunnable(new Runnable() {
            @Override
            public void run() {
               SecondScene secondScene = new SecondScene();
               pop();
               pop();
               secondScene.push();
            }
        });
        Manu.setOnClickRunnable(new Runnable() {
            @Override
            public void run() {
                pop();
                pop();
            }
        });
        gameWorld.add(Layer.ui.ordinal(), Manu);
        gameWorld.add(Layer.ui.ordinal(), button);
    }
}
