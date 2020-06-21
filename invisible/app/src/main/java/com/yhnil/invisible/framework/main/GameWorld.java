package com.yhnil.invisible.framework.main;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.yhnil.invisible.framework.iface.Recyclable;
import com.yhnil.invisible.framework.iface.Touchable;

import java.util.ArrayList;

public class GameWorld {
    protected RecyclePool recyclePool = new RecyclePool();
    protected ArrayList<ArrayList<GameObject>> layers;
    protected ArrayList<GameObject> trash = new ArrayList<>();
    protected Touchable capturingObject;

    public GameWorld(int layerCount) {
        layers = new ArrayList<>(layerCount);
        for (int i = 0; i < layerCount; i++) {
            layers.add(new ArrayList<GameObject>());
        }
    }

    public ArrayList<GameObject> objectsAtLayer(int layer) {
        return layers.get(layer);
    }

    public void draw(Canvas canvas) {
        for (ArrayList<GameObject> objects: layers) {
            for (GameObject o : objects) {
                o.draw(canvas);
            }
        }
    }

    public void update() {
        for (ArrayList<GameObject> objects: layers) {
            for (GameObject o : objects) {
                o.update();
            }
        }
        if (trash.size() > 0) {
            clearTrash();
        }
    }

    public void add(final int layerIndex, final GameObject obj) {
        ArrayList<GameObject> objects = layers.get(layerIndex);
        int index = objects.indexOf(obj);
        if (index >= 0) {
            return;
        }

        UiBridge.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> objects = layers.get(layerIndex);
                objects.add(obj);
            }
        });
    }
    private void clearTrash() {
        for (int ti = trash.size() - 1; ti >= 0; ti--) {
            GameObject o = trash.get(ti);
            for (ArrayList<GameObject> objects : layers) {
                int i = objects.indexOf(o);
                if (i >= 0) {
                    objects.remove((i));
                    break;
                }
            }
            trash.remove(ti);
            if (o instanceof Recyclable) {
                ((Recyclable) o).recycle();
                recyclePool.add(o);
            }
        }
    }

    public RecyclePool getRecyclePool() {
        return recyclePool;
    }

    public void captureTouch(Touchable obj) {
        capturingObject = obj;
    }
    public void releaseTouch() {
        capturingObject = null;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if (capturingObject != null) {
            return capturingObject.onTouchEvent(event);
        }
        for (ArrayList<GameObject> objects: layers) {
            for (GameObject o : objects) {
                if (o instanceof Touchable) {
                    boolean ret = ((Touchable) o).onTouchEvent(event);
                    if (ret) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void removeObject(GameObject gameObject) {
        trash.add(gameObject);
    }
}
