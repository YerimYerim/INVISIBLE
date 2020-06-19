package com.yhnil.invisible.game.obj.sobj;

import android.graphics.Color;
import android.util.Log;

import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.obj.ShapeObject;

public class DangerZone extends ShapeObject {
    private GameTimer timer;

    public float startingTime = 2;
    public float lifeTime = 2;
    public float endingTime = 2;

    public enum LightState { None, Enter, Stay, Exit, Count };
    public LightState lightState = LightState.None;

    public DangerZone(float x, float y) {
        super(x, y);
        setColor(Color.MAGENTA);
        setSector(100);

        timer = new GameTimer(255, (int) (255 / startingTime));
    }

    public void update() {
        switch (lightState){
            case None:
                setAlpha(0);
                break;
            case Enter:
                setAlpha(Math.min(timer.getIndex(), 255));
                if(timer.done()) {
                    lightState = LightState.Stay;
                    timer.set(255, (int) (255 / lifeTime));
                }
                break;
            case Stay:
                setAlpha(255);
                if(timer.done()) {
                    lightState = LightState.Exit;
                    timer.set(255, (int) (255 / endingTime));
                }
                break;
            case Exit:
                setAlpha(Math.max(255 - timer.getIndex(), 0));
                if(timer.done()) {
                    lightState = LightState.None;
                }
                break;
            case Count:
                // Exception
                break;
        }
    }

    public void turnOn() {
        lightState = LightState.Enter;
        timer = new GameTimer(255, (int) (255 / startingTime));
    }
}
