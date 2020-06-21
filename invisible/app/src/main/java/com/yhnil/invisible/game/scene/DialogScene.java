package com.yhnil.invisible.game.scene;


import com.yhnil.invisible.R;
import com.yhnil.invisible.framework.main.UiBridge;
import com.yhnil.invisible.framework.obj.BitmapObject;
import com.yhnil.invisible.framework.obj.ui.Button;

public class DialogScene extends com.yhnil.invisible.framework.main.GameScene {
    public enum Layer {
        bg, corestone, light, stone, player, ui, joystick, COUNT
    }

    @Override
    protected int getLayerCount() {
        return Layer.COUNT.ordinal();
    }

    @Override
    public void enter() {
        super.enter();
        setTransparent(true);
        initObjects();
    }

    private void initObjects() {
        int screenWidth = UiBridge.metrics.size.x;
        int screenHeight = UiBridge.metrics.size.y;

        int cx = UiBridge.metrics.center.x;
        int cy = UiBridge.metrics.center.y;

        gameWorld.add(Layer.bg.ordinal(), new BitmapObject(cx, cy, screenWidth, screenHeight, R.mipmap.black_transparent));

        Button button = new Button(cx, cy, R.mipmap.btn_start_game, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        button.setOnClickRunnable(new Runnable() {
            @Override
            public void run() {
                pop();
            }
        });
        gameWorld.add(Layer.ui.ordinal(), button);
    }
}
