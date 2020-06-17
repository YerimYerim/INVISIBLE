package com.yhnil.invisible.game.obj;

import android.graphics.Color;
import com.yhnil.invisible.framework.obj.ShapeObject;
import com.yhnil.invisible.framework.obj.ui.Joystick;
import com.yhnil.invisible.game.obj.sobj.Stone;

public class Player extends ShapeObject {

    private Joystick joystick = new Joystick();
    public Player(float x, float y) {
        super(x, y);
        setCircle(10);
        setColor(Color.GRAY);
    }

    public void connectJoystick(Joystick joystick)
    {
        this.joystick = joystick;
    }

    public void update() {
   //     if(joystick.onTouchEvent()
         x = x+joystick.getDirection().x;
         y = y+joystick.getDirection().y;
    }
}
