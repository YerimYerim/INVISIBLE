package com.yhnil.invisible.game.obj;

import android.graphics.Color;
import android.util.Log;

import com.yhnil.invisible.framework.iface.CircleCollidable;
import com.yhnil.invisible.framework.main.GameObject;
import com.yhnil.invisible.framework.obj.ShapeObject;
import com.yhnil.invisible.framework.obj.ui.Joystick;
import com.yhnil.invisible.framework.util.CollisionHelper;
import com.yhnil.invisible.framework.util.Vector;
import com.yhnil.invisible.game.scene.OverScene;
import com.yhnil.invisible.game.scene.SecondScene;

import java.util.ArrayList;

public class Player extends ShapeObject implements CircleCollidable{

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
        checkItemCollision();
        checkPlayGroundCollision();
    }

    private void checkItemCollision() {
        ArrayList<GameObject> items = SecondScene.get().getGameWorld().objectsAtLayer(SecondScene.Layer.stone.ordinal());

        for (GameObject obj : items) {
            if (!(obj instanceof CircleCollidable))
                continue;
            if (CollisionHelper.collides(this, (CircleCollidable) obj))
            {
                SecondScene.get().scoreObject.add(5);
                setColor(((ShapeObject)obj).getColor());
                obj.remove();
            }
        }
        float myDegree = (float) (Math.atan2(y, x) / Math.PI * 180.f);
        float other = SecondScene.get().dangerZone.getDegree();

        myDegree = (myDegree + 360) % 360;

        float gap = Math.abs(other - myDegree) % 360;
        if(getColor() != SecondScene.get().dangerZone.getColor()){
            if(gap < 30 || gap > 330) {
                OverScene scene = new OverScene();
                scene.push();
            }
        }
    }
    private void checkPlayGroundCollision() {
        ArrayList<GameObject> items = SecondScene.get().getGameWorld().objectsAtLayer(SecondScene.Layer.bg.ordinal());

        for (GameObject obj : items) {
            if (!(obj instanceof CircleCollidable))
            {
                continue;
            }
            if (!CollisionHelper.collides(this, (CircleCollidable) obj))
            {
                x = x-joystick.getDirection().x;
                y = y-joystick.getDirection().y;
            }
        }
    }
    @Override
    public float getCircle(Vector position) {
        position.x = x;
        position.y = y;
        return getRadius();
    }
}
