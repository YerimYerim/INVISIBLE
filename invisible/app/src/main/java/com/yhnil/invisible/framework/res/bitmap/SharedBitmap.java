package com.yhnil.invisible.framework.res.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.yhnil.invisible.framework.main.UiBridge;

import java.util.HashMap;

public class SharedBitmap {
    private static final String TAG = SharedBitmap.class.getSimpleName();
    private static HashMap<Integer, SharedBitmap> map = new HashMap<>();
    private static BitmapFactory.Options noScaleOption;
    static {
        noScaleOption = new BitmapFactory.Options();
        noScaleOption.inScaled = false;
    }
    Bitmap bitmap;
    int width;
    int height;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public void draw(Canvas canvas, float x, float y) {
        canvas.drawBitmap(bitmap, x, y, null);
    }
    public void draw(Canvas canvas, float x, float y, Paint paint) {
        canvas.drawBitmap(bitmap, x, y, paint);
    }
    public void draw(Canvas canvas, Matrix matrix, Paint paint) {
        canvas.drawBitmap(bitmap, matrix, paint);
    }

    public static SharedBitmap load(int resId) {
        return load(resId, false);
    }
    public static SharedBitmap load(int resId, boolean inScaled) {
//        Log.d(TAG, "Loading " + resId);
        SharedBitmap sb = map.get(resId);
        if (sb != null) {
            return sb;
        }

        BitmapFactory.Options opt = inScaled ? null : noScaleOption;
        Bitmap bitmap = BitmapFactory.decodeResource(UiBridge.getResources(), resId, opt);
        if (bitmap == null) {
            return null;
        }
        sb = new SharedBitmap();
        sb.bitmap = bitmap;
        sb.width = bitmap.getWidth();
        sb.height = bitmap.getHeight();
        map.put(resId, sb);

        return sb;
    }

    public static void unload(int resId) {
        map.remove(resId);
    }
    public static void unloadAll(int resId) {
        map.clear();
    }

    private SharedBitmap() {}
}
