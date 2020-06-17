package com.yhnil.invisible.game.scene;

import com.yhnil.invisible.R;
import com.yhnil.invisible.framework.input.sensor.GyroSensor;
import com.yhnil.invisible.framework.main.GameScene;
import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.main.UiBridge;
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

    public enum Layer {
        bg, enemy, player, ui, COUNT , joystick
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
    }

    @Override
    public void enter() {
        super.enter();
        initObjects();
    }

    @Override
    public void exit() {
        super.exit();
    }

    private void initObjects() {
        timer = new GameTimer(60, 1);

        gameWorld.add(FirstScene.Layer.enemy.ordinal(), new PlayGround(0, 0));
        Core core = new Core(0, 0);
        gameWorld.add(FirstScene.Layer.enemy.ordinal(), core);
        gameWorld.add(FirstScene.Layer.enemy.ordinal(), new Stone(0, 0));
        DangerZone dangerZone = new DangerZone(0, 0);
        gameWorld.add(FirstScene.Layer.enemy.ordinal(), dangerZone);

        core.connectDangerZone(dangerZone);

        joystick = new Joystick();

        player = new Player(0, 0);
        player.connectJoystick(joystick);
        gameWorld.add(SecondScene.Layer.player.ordinal(), player);
        gameWorld.add(SecondScene.Layer.ui.ordinal(), joystick);



        gameWorld.add(FirstScene.Layer.enemy.ordinal(), new CoreStone(0, 0));
    }

}