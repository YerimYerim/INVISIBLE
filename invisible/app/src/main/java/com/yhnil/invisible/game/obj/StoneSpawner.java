package com.yhnil.invisible.game.obj;

import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.game.obj.sobj.State;
import com.yhnil.invisible.game.obj.sobj.Stone;

import java.util.Random;

import static com.yhnil.invisible.game.obj.sobj.State.begin;
import static com.yhnil.invisible.game.obj.sobj.State.end;

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
            if(getDistFromCenter(stone) )
            {
                stone.state = end;
            }
            else if(stone.state == end)
            {
                stone.state = begin;
            }
        }
    }
}
