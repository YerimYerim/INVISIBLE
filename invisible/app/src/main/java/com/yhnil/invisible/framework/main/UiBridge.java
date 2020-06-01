package com.yhnil.invisible.framework.main;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class UiBridge {
    public static int x(int size) {
        int mdpi = size * metrics.oneInch / 160;
        if (mdpi < 0) {
            mdpi += metrics.size.x;
        }
        return mdpi;
    }
    public static int y(int size) {
        int mdpi = size * metrics.oneInch / 160;
        if (mdpi < 0) {
            mdpi += metrics.size.y;
        }
        return mdpi;
    }

    public static class Metrics {
        public int oneInch;
        public Point size;
        public Point center;
    }
    public static Metrics metrics = new Metrics();
    public static void setActivity(Activity activity) {
        UiBridge.activity = activity;
        WindowManager wm = activity.getWindowManager();
        Point size = new Point();
        Display display = wm.getDefaultDisplay();
        display.getSize(size);
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        size.y -= getStatusBarHeight();
        metrics.size = size;
        metrics.center = new Point(size.x / 2, size.y / 2);
        metrics.oneInch = dm.densityDpi; //400dpi, 120 400 * 120 / 160
    }

    public static void setView(View view) {
        UiBridge.view = view;
        UiBridge.resources = view.getResources();
    }

    public static void exit() {
        activity.finish();
    }
    public static void post(Runnable runnable) {
        view.post(runnable);
    }

    private static Activity activity;
    private static View view;

    public static Resources getResources() {
        return resources;
    }

    private static Resources resources;

    private static int getStatusBarHeight() {
        int height = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = activity.getResources().getDimensionPixelSize(resourceId);
        }

        return height;
    }
}
