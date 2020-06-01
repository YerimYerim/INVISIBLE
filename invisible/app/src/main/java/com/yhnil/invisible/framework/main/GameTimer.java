package com.yhnil.invisible.framework.main;

public class GameTimer {
    private static final long NANOS_IN_ONE_SECOND = 1_000_000_000;
    private static final float NANOS_IN_ONE_SECOND_FLOAT = 1_000_000_000f;
    private static long currentTimeNanos;
    private static long diffNanos;

    public static void setCurrentTimeNanos(long timeNanos) {
        diffNanos = timeNanos - currentTimeNanos;
        currentTimeNanos = timeNanos;
    }
    public static long getCurrentTimeNanos() {
        return currentTimeNanos;
    }
    public static long getTimeDiffNanos() {
        return diffNanos;
    }
    public static float getTimeDiffSeconds() {
        return diffNanos / NANOS_IN_ONE_SECOND_FLOAT;
    }

    protected final int count;
    protected final int fps;
    protected long time;

    public GameTimer(int count, int framesPerSecond) {
        this.count = count;
        this.fps = framesPerSecond;
        this.time = currentTimeNanos;
    }

    public int getRawIndex() {
        long elapsed = currentTimeNanos - this.time;
        return (int) (((elapsed * fps + NANOS_IN_ONE_SECOND / 2) / NANOS_IN_ONE_SECOND));
    }
    public int getIndex() {
        int index = getRawIndex();
        return index % count;
    }
    public boolean done() {
        int index = getRawIndex();
        return index >= count;
    }

    public void reset() {
        this.time = currentTimeNanos;
    }
}
