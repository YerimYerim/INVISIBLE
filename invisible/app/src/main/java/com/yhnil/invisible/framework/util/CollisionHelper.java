package com.yhnil.invisible.framework.util;

import android.graphics.RectF;

import com.yhnil.invisible.framework.iface.BoxCollidable;
import com.yhnil.invisible.framework.main.GameObject;

public class CollisionHelper {
    private static RectF rect1 = new RectF();
    private static RectF rect2 = new RectF();
    public static boolean collides(BoxCollidable o1, BoxCollidable o2) {
        o1.getBox(rect1);
        o2.getBox(rect2);
        if (rect1.left > rect2.right) {
            return false;
        }
        if (rect1.right < rect2.left) {
            return false;
        }
        if (rect1.top > rect2.bottom) {
            return false;
        }
        if (rect1.bottom < rect2.top) {
            return false;
        }
        return true;
    }

    public static float getDistanceSquare(GameObject o1, GameObject o2) {
        float dx = o1.getX() - o2.getX();
        float dy = o1.getY() - o2.getY();
        return dx * dx + dy * dy;
    }
    public static float getDistance(GameObject o1, GameObject o2) {
        return (float) Math.sqrt(getDistanceSquare(o1, o2));
    }
}
