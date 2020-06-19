package com.yhnil.invisible.game.obj;

import com.yhnil.invisible.game.obj.sobj.Stone;

public class StoneSpawner {

    Stone stones[];
    float MaxDistance  = 100;
    StoneSpawner(Stone stones[] , float maxDistance){
        this.stones = new Stone[10];
        this.stones = stones;
        this.MaxDistance = maxDistance;
    }
    boolean getDistFromCenter(Stone stone){
        float dist = (float) Math.sqrt(stone.getX() * stone.getX() + stone.getY() * stone.getY() );
        if (dist > MaxDistance - stone.getRadius())
            return false;
        else
            return true;
    }
    public void update() {
        for (Stone stone: stones)
        {
        }
    }
}
