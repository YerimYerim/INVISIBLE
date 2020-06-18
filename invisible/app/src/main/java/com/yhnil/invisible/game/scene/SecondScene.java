package com.yhnil.invisible.game.scene;

import android.graphics.Color;
import android.graphics.RectF;

import com.yhnil.invisible.R;
import com.yhnil.invisible.framework.input.sensor.GyroSensor;
import com.yhnil.invisible.framework.main.GameScene;
import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.main.UiBridge;
import com.yhnil.invisible.framework.obj.ScoreObject;
import com.yhnil.invisible.framework.obj.ui.Joystick;
import com.yhnil.invisible.game.obj.Player;
import com.yhnil.invisible.game.obj.HorzScrollBackground;
import com.yhnil.invisible.game.obj.sobj.Core;
import com.yhnil.invisible.game.obj.sobj.CoreStone;
import com.yhnil.invisible.game.obj.sobj.DangerZone;
import com.yhnil.invisible.game.obj.sobj.PlayGround;
import com.yhnil.invisible.game.obj.sobj.Stone;

import java.util.Random;

public class SecondScene extends GameScene {
    private static final String TAG = SecondScene.class.getSimpleName();
    private Joystick joystick;
    public ScoreObject scoreObject;
    public DangerZone dangerZone;

    public enum Layer {
        bg, corestone, light, stone, player, ui, joystick, COUNT
    }

    private Player player;

    private GameTimer timer;
    private static SecondScene instance;

    @Override
    protected int getLayerCount() {
        return Layer.COUNT.ordinal();
    }

    @Override
    public void update() {
        super.update();

        if (timer.done()) {
            gameWorld.add(Layer.stone.ordinal(), new Stone(0, 0));
            timer.reset();
        }

    }

    @Override
    public void enter() {
        super.enter();
        instance = this;
        initObjects();
    }

    @Override
    public void exit() {
        super.exit();
    }

    private void initObjects() {
        RectF rbox = new RectF(UiBridge.x(-52), UiBridge.y(20), UiBridge.x(-20), UiBridge.y(62));
        scoreObject = new ScoreObject(R.mipmap.number_64x84, rbox);
        gameWorld.add(Layer.ui.ordinal(), scoreObject);
        timer = new GameTimer(4, 1);

        gameWorld.add(SecondScene.Layer.bg.ordinal(), new PlayGround(0, 0));

        Core core = new Core(0, 0);
        gameWorld.add(Layer.corestone.ordinal(), core);

        dangerZone = new DangerZone(0, 0);
        core.connectDangerZone(dangerZone);
        gameWorld.add(Layer.light.ordinal(), dangerZone);


        player = new Player(0, 0);
        gameWorld.add(Layer.player.ordinal(), player);

        joystick = new Joystick();
        player.connectJoystick(joystick);
        gameWorld.add(Layer.joystick.ordinal(), joystick);

        int colors[] = {
                Color.RED,
                Color.GREEN,
                Color.BLUE,
                Color.CYAN,
                Color.MAGENTA,
                Color.YELLOW
        };

        int index = 0;
        for(int color : colors)
        {
            CoreStone coreStone = new CoreStone(0, 0, color);
            core.connectCoreStone(coreStone, index++);
            gameWorld.add(SecondScene.Layer.corestone.ordinal(), coreStone);
        }
    }

    public static SecondScene get() {
        return instance;
    }
}