package com.yhnil.invisible.game.obj;

import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.game.obj.sobj.Stone;

import java.util.Random;

public class StoneSpawner {

    float degree = 0;
    float speed  = 0;
   // float distanceFromCenter; // rotate degree per second
    Stone stones[] = new Stone[10];
    StoneSpawner(){

    }
    public void update() {
        for (int i = 0; i < 10; ++i)
        {
            float x = (float) (stones[i].getX() + speed * Math.cos(Math.toRadians(degree)));
            float y = (float) (stones[i].getY() + speed * Math.sin(Math.toRadians(degree)));
        }
    }
}
