package com.yhnil.invisible.game.scene;

import com.yhnil.invisible.R;
import com.yhnil.invisible.framework.input.sensor.GyroSensor;
import com.yhnil.invisible.framework.main.GameScene;
import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.main.UiBridge;
import com.yhnil.invisible.framework.obj.BitmapObject;
import com.yhnil.invisible.game.obj.Player;
import com.yhnil.invisible.game.obj.HorzScrollBackground;

import java.util.Random;

public class SecondScene extends GameScene {
    private static final String TAG = SecondScene.class.getSimpleName();
    private BitmapObject backgoundOval;

    public enum Layer {
        bg, enemy, player, ui, COUNT
    }

    private Player player;

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
            pop();
        }

    }

    @Override
    public void enter() {
        super.enter();
        GyroSensor.get();
        initObjects();
    }

    @Override
    public void exit() {
        GyroSensor.get().destroy();
        super.exit();
    }

    private void initObjects() {

        timer = new GameTimer(60, 1);
        Random rand = new Random();
        int mdpi_100 = UiBridge.x(100);
        int sw = UiBridge.metrics.size.x;
        int sh = UiBridge.metrics.size.y;
        int cx = UiBridge.metrics.center.x;
        int cy = UiBridge.metrics.center.y;
        player = new Player(mdpi_100, sh - mdpi_100);
        gameWorld.add(Layer.enemy.ordinal(), player);
        gameWorld.add(Layer.bg.ordinal(), new HorzScrollBackground(R.mipmap.cookie_run_bg_1));
    }

}