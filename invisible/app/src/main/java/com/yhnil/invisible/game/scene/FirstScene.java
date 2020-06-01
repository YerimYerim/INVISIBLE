package com.yhnil.invisible.game.scene;

import android.graphics.RectF;

import com.yhnil.invisible.R;
import com.yhnil.invisible.framework.main.GameScene;
import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.main.UiBridge;
import com.yhnil.invisible.framework.obj.BitmapObject;
import com.yhnil.invisible.framework.obj.ScoreObject;
import com.yhnil.invisible.framework.obj.ui.Button;
import com.yhnil.invisible.game.obj.Ball;
import com.yhnil.invisible.game.obj.CityBackground;

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
        initObjects();
    }

    private void initObjects() {
        Random rand = new Random();
        int mdpi_100 = UiBridge.x(100);
        for (int i = 0; i < 10; i++) {
            int dx = rand.nextInt(2 * mdpi_100) - 1 * mdpi_100;
            if (dx >= 0) dx++;
            int dy = rand.nextInt(2 * mdpi_100) - 1 * mdpi_100;
            if (dy >= 0) dy++;
            ball = new Ball(mdpi_100, mdpi_100, dx, dy);
            gameWorld.add(Layer.enemy.ordinal(), ball);
        }
        gameWorld.add(Layer.bg.ordinal(), new CityBackground());
        int screenWidth = UiBridge.metrics.size.x;
        RectF rbox = new RectF(UiBridge.x(-52), UiBridge.y(20), UiBridge.x(-20), UiBridge.y(62));
        scoreObject = new ScoreObject(R.mipmap.number_64x84, rbox);
        gameWorld.add(Layer.ui.ordinal(), scoreObject);
        BitmapObject title = new BitmapObject(UiBridge.metrics.center.x, UiBridge.y(160), -150, -150, R.mipmap.slap_fight_title);
        gameWorld.add(Layer.ui.ordinal(), title);
        timer = new GameTimer(2, 1);

        int cx = UiBridge.metrics.center.x;
        int y = UiBridge.metrics.center.y;
//        y += UiBridge.y(100);
        gameWorld.add(Layer.ui.ordinal(), new Button(cx, y, R.mipmap.btn_tutorial, R.mipmap.blue_round_btn, R.mipmap.red_round_btn));
        y += UiBridge.y(100);
        gameWorld.add(Layer.ui.ordinal(), new Button(cx, y, R.mipmap.btn_start_game, R.mipmap.blue_round_btn, R.mipmap.red_round_btn));
        y += UiBridge.y(100);
        gameWorld.add(Layer.ui.ordinal(), new Button(cx, y, R.mipmap.btn_highscore, R.mipmap.blue_round_btn, R.mipmap.red_round_btn));
    }
}
