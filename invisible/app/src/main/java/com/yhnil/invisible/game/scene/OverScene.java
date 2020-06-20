package com.yhnil.invisible.game.scene;

import android.graphics.RectF;

import com.yhnil.invisible.R;
import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.main.UiBridge;
import com.yhnil.invisible.framework.obj.ScoreObject;
import com.yhnil.invisible.framework.obj.ui.Button;

public class OverScene extends com.yhnil.invisible.framework.main.GameScene {
    private static final String TAG = OverScene.class.getSimpleName();
    public ScoreObject scoreObject;
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

        gameWorld.add(Layer.ui.ordinal(), scoreObject);
        Button button = new Button(cx, y, R.mipmap.start, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        Button Manu = new Button(cx, y2, R.mipmap.manu, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        button.setOnClickRunnable(new Runnable() {
            @Override
            public void run() {
               GameScene gameScene = new GameScene();
               pop();
               gameScene.push();
            }
        });
        Manu.setOnClickRunnable(new Runnable() {
            @Override
            public void run() {
                pop();
                MenuScene menuScene = new MenuScene();
                menuScene.push();

            }
        });
        gameWorld.add(Layer.ui.ordinal(), Manu);
        gameWorld.add(Layer.ui.ordinal(), button);
    }

    public void getScore(ScoreObject Scoreobject) {
        scoreObject = Scoreobject;
        RectF rbox = new RectF(UiBridge.metrics.center.x-UiBridge.x(16), UiBridge.y(124), UiBridge.metrics.center.x+UiBridge.x(16), UiBridge.y(186));
        scoreObject.setRect(rbox);
    }
}
