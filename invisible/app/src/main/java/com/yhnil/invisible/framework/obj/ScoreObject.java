package com.yhnil.invisible.framework.obj;

import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.yhnil.invisible.framework.main.GameObject;
import com.yhnil.invisible.framework.res.bitmap.SharedBitmap;

public class ScoreObject extends GameObject {
    private static final String TAG = ScoreObject.class.getSimpleName();
    private final SharedBitmap sbmp;
    private int digitWidth;
    private RectF rightmostRect;
    private RectF rect = new RectF();
    private Rect srcRect = new Rect();
    private ObjectAnimator scoreAnimator = ObjectAnimator.ofInt(this, "displayedScore", 0, 1);

    public enum Align {Left, Center, Right, Count};
    Align align = Align.Right;

    public int getDisplayedScore() {
        return displayedScore;
    }

    public void setDisplayedScore(int displayedScore) {
        this.displayedScore = displayedScore;
    }
    public int getScoreValue() {
        return targetScore;
    }

    private int displayedScore, targetScore;

    public ScoreObject(int resId, RectF rightmostRect) {
        sbmp = SharedBitmap.load(resId, false);
        digitWidth = sbmp.getWidth() / 10;
        int digitHeight = sbmp.getHeight();
        this.rightmostRect = new RectF(rightmostRect);
        if (this.rightmostRect.bottom == 0) {
            float width = this.rightmostRect.width();
            float height = width / digitWidth * digitHeight;
            this.rightmostRect.bottom = this.rightmostRect.top + height;
        }
        srcRect.top = 0;
        srcRect.bottom = digitHeight;
        scoreAnimator.setDuration(300);
    }

    public void setRect(RectF rightmostRect ) {
        digitWidth = sbmp.getWidth() / 10;
        int digitHeight = sbmp.getHeight();
        this.rightmostRect = new RectF(rightmostRect);
        if (this.rightmostRect.bottom == 0) {
            float width = this.rightmostRect.width();
            float height = width / digitWidth * digitHeight;
            this.rightmostRect.bottom = this.rightmostRect.top + height;
        }
        srcRect.top = 0;
        srcRect.bottom = digitHeight;
        scoreAnimator.setDuration(300);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        rect.set(rightmostRect);
        float width = rect.width();
        int score = this.displayedScore;
        if(align == Align.Center){
            int cnt = (int) (Math.log10(score)+1);
            rect.left += (width * cnt) / 2;
            rect.right += (width * cnt) / 2;
        }
        while (score > 0) {
            int digit = score % 10;
            srcRect.left = digitWidth * digit;
            srcRect.right = srcRect.left + digitWidth;
//            Log.d(TAG, "digit=" + digit + " src=" + srcRect + " dst=" + rect);
            canvas.drawBitmap(sbmp.getBitmap(), srcRect, rect, null);

            rect.left -= width;
            rect.right -= width;

            score /= 10;
        }
        if (displayedScore == 0){
            int digit = score % 10;
            srcRect.left = digitWidth * digit;
            srcRect.right = srcRect.left + digitWidth;
            canvas.drawBitmap(sbmp.getBitmap(), srcRect, rect, null);
        }
    }

    public void reset() {
        add(-targetScore);
    }
    public void add(int score) {
        int newScore = this.targetScore + score;
        scoreAnimator.setIntValues(this.targetScore, newScore);
        scoreAnimator.start();
        this.targetScore = newScore;
    }

    public void setAlign(Align align) {
        this.align = align;
    }
}
