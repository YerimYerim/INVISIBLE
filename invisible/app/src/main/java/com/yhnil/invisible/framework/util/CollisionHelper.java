package com.yhnil.invisible.framework.util;

import android.graphics.RectF;

import com.yhnil.invisible.framework.iface.BoxCollidable;
import com.yhnil.invisible.framework.iface.CircleCollidable;
import com.yhnil.invisible.framework.main.GameObject;

public class CollisionHelper {
    private static RectF rect1 = new RectF();
    private static RectF rect2 = new RectF();
    private static Vector pos1 = new Vector();
    private static Vector pos2 = new Vector();
    public static boolean collide(GameObject o1, GameObject o2){
        BoxCollidable bc1 = null, bc2 = null;
        CircleCollidable cc1 = null, cc2 = null;

        if(o1 instanceof BoxCollidable)
            bc1 = (BoxCollidable) o1;
        if(o2 instanceof BoxCollidable)
            bc2 = (BoxCollidable) o1;
        if(o1 instanceof CircleCollidable)
            cc1 = (CircleCollidable) o1;
        if(o2 instanceof CircleCollidable)
            cc2 = (CircleCollidable) o1;

        if(bc1 != null) {
            if(bc2 != null)
                collides(bc1, bc2);
        }
        else if(cc1 != null) {
            if(cc2 != null)
                collides(cc1, cc2);
        }
        else
            return false;
        return false;
    }
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
    public static boolean collides(CircleCollidable o1, CircleCollidable o2) {
        float radius1 = o1.getCircle(pos1);
        float radius2 = o2.getCircle(pos2);

        if(getDistance((GameObject)o1, (GameObject)o2) > radius1 + radius2)
            return false;
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
