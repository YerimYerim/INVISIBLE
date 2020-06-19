package com.yhnil.invisible.game.scene;

import android.graphics.RectF;

import com.yhnil.invisible.R;
import com.yhnil.invisible.framework.main.GameObject;
import com.yhnil.invisible.framework.main.GameScene;
import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.main.UiBridge;
import com.yhnil.invisible.framework.obj.BitmapObject;
import com.yhnil.invisible.framework.obj.ScoreObject;
import com.yhnil.invisible.framework.obj.ui.Button;

public class FirstScene extends GameScene {
    private static final String TAG = FirstScene.class.getSimpleName();

    public enum Layer {
        bg, enemy, player, ui, COUNT
    }

    private ScoreObject scoreObject;
    private GameTimer timer;
    private  int x;
    BitmapObject Logo;
    @Override
    protected int getLayerCount() {
        return Layer.COUNT.ordinal();
    }

    @Override
    public void update() {
        super.update();
        if (timer.done()) {
            if(x < 255)
            {x+=20;}
            Logo.setPaintAlpha(x);
            timer.reset();
        }
    }

    @Override
    public void enter() {
        super.enter();
        initObjects();
    }

    private void initObjects() {
        x = 0;
        timer = new GameTimer(1, 1);
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

        Logo = new BitmapObject(cx,y - 600, 1000, 400, R.mipmap.logo);
        Logo.setPaintAlpha(x);
        gameWorld.add(Layer.ui.ordinal(),Logo);
        gameWorld.add(Layer.ui.ordinal(), button);
    }
}
