package com.yhnil.invisible.game.obj;

import android.graphics.Color;
import android.util.Log;

import com.yhnil.invisible.R;
import com.yhnil.invisible.framework.iface.CircleCollidable;
import com.yhnil.invisible.framework.main.GameObject;
import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.obj.ShapeObject;
import com.yhnil.invisible.framework.obj.ui.Joystick;
import com.yhnil.invisible.framework.res.sound.SoundEffects;
import com.yhnil.invisible.framework.util.CollisionHelper;
import com.yhnil.invisible.framework.util.Vector;
import com.yhnil.invisible.game.obj.sobj.Core;
import com.yhnil.invisible.game.obj.sobj.DangerZone;
import com.yhnil.invisible.game.obj.sobj.Stone;
import com.yhnil.invisible.game.scene.OverScene;
import com.yhnil.invisible.game.scene.GameScene;

import java.util.ArrayList;

import static com.yhnil.invisible.framework.main.GameScene.pop;

public class Player extends ShapeObject implements CircleCollidable{
    private Joystick joystick = null;
    private Core core = null;
    public ArrayList<Stone> stones = new ArrayList<>();
    private float dps = 45;
    public Player(float x, float y) {
        super(x, y);
        setCircle(10);
        setColor(Color.GRAY);
    }

    public void connectJoystick(Joystick joystick) {
        this.joystick = joystick;
    }
    public void connectCcore(Core core) {
        this.core = core;
    }

    public void update() {
        // degree update
        float dt = GameTimer.getTimeDiffSeconds();
        degree += (dps * dt) % 360;

        x = x+joystick.getDirection().x;
        y = y+joystick.getDirection().y;

        checkDangerZoneCollision();
        checkStoneCollision();
        checkPlayGroundCollision();

        float r = 12;
        int index = 0;
        for(Stone stone : stones) {
            float theta = (float) ((60 * index++ + degree) / 180 * Math.PI);
            stone.setX((float) (x+r*Math.cos(theta)));
            stone.setY((float) (y+r*Math.sin(theta)));
        }
    }

    private void checkDangerZoneCollision() {
        DangerZone dangerZone = GameScene.get().dangerZone;
        float myDegree = (float) (Math.atan2(y, x) / Math.PI * 180.f);
        float other = dangerZone.getDegree();

        myDegree = (myDegree + 360) % 360;

        float gap = Math.abs(other - myDegree) % 360;

        boolean isInLight = false;
        if (dangerZone.lightState == DangerZone.LightState.Stay) {
            if (gap < 30 || gap > 330) {
                if (getColor() != dangerZone.getColor()) {
                    OverScene scene = new OverScene();
                    scene.getScore(GameScene.scoreObject);
                    pop();
                    scene.push();
                } else if (stones.size() == 6 &&CollisionHelper.collides(this, (CircleCollidable) core) ) {
                    isInLight = true;
                    core.coreStones[core.dangerZoneIndex].setColor(Color.GRAY);
                    SoundEffects.get().play(R.raw.corebreak);
                }
            }
        }

        if (CollisionHelper.collides(this, (CircleCollidable) core)) {
            if(isInLight){
                {
                    GameScene.get().scoreObject.add(100);
                    core.grayZoneIndex++;
                }
                for (Stone stone : stones)
                    stone.remove();
                stones.clear();
            }else{
                double distance = Math.sqrt(x * x + y * y);
                distance = 18+getRadius() - distance;
                float radian = (float) (Math.atan2(y, x));
                x += distance * Math.cos(radian);
                y += distance * Math.sin(radian);
            }
        }
    }

    private void checkStoneCollision() {
        ArrayList<GameObject> items = GameScene.get().getGameWorld().objectsAtLayer(GameScene.Layer.stone.ordinal());

        for (GameObject obj : items) {
            if (!(obj instanceof CircleCollidable))
                continue;
            if (CollisionHelper.collides(this, (CircleCollidable) obj))
            {
                if(((Stone) obj).state == Stone.StoneState.Normal) {
                    GameScene.get().scoreObject.add(5);
                    setColor(((ShapeObject) obj).getColor());
                    SoundEffects.get().play(R.raw.stoneeat);
                    boolean isContain = false;
                    for(Stone stone : stones)
                        if(stone.getColor() == ((Stone) obj).getColor()){
                            isContain = true;
                            break;
                        }

                    if(isContain){
                        obj.remove();
                    }
                    else{
                        ((Stone) obj).state = Stone.StoneState.Contain;
                        stones.add((Stone) obj);
                    }
                }
            }
        }
    }
    private void checkPlayGroundCollision() {
        ArrayList<GameObject> items = GameScene.get().getGameWorld().objectsAtLayer(GameScene.Layer.bg.ordinal());

        for (GameObject obj : items) {
            if (!(obj instanceof CircleCollidable)) {
                continue;
            }
            if (!CollisionHelper.collides(this, (CircleCollidable) obj)) {
               // x += joystick.getDirection().x;
               // y += joystick.getDirection().y;
                double distance = Math.sqrt(x * x + y * y);
                distance -= obj.getRadius()-getRadius();
                float radian = (float) (Math.atan2(y, x));
                x -= distance * Math.cos(radian);
                y -= distance * Math.sin(radian);
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
