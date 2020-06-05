package com.yhnil.invisible.game.obj;

import android.util.Log;
import android.view.MotionEvent;

import com.yhnil.invisible.R;
import com.yhnil.invisible.framework.iface.Touchable;
import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.main.UiBridge;
import com.yhnil.invisible.framework.obj.AnimObject;
import com.yhnil.invisible.framework.res.bitmap.FrameAnimationBitmap;

public class Player extends AnimObject implements Touchable {

    private static final float JUMP_POWER = -1500;
    private static final float GRAVITY_SPEED = 4500;
    private static final String TAG = Player.class.getSimpleName();
    private final FrameAnimationBitmap fabNormal;
    private final FrameAnimationBitmap fabJump;
    private boolean jumping;
    private float speed;
    private float base;

    public Player(float x, float y) {
        super(x, y, -50, -50, R.mipmap.cookie_run, 12, 0);
        base = y;

        fabNormal = fab;
        fabJump = new FrameAnimationBitmap(R.mipmap.cookie_jump, 12, 0);
    }

    public enum AnimState {
        normal, jump
    }
    public void setAnimState(AnimState state) {
        if (state == AnimState.normal) {
            fab = fabNormal;
        } else {
            fab = fabJump;
        }
    }

    @Override
    public void update() {
        if (jumping) {
            float timeDiffSeconds = GameTimer.getTimeDiffSeconds();
            y += speed * timeDiffSeconds;
            speed += GRAVITY_SPEED * timeDiffSeconds;
            if (y >= base) {
                Log.d(TAG, "Jumping Done");
                jumping = false;
                setAnimState(AnimState.normal);
                y = base;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() != MotionEvent.ACTION_DOWN) {
            return false;
        }
        float tx = e.getX();
//        Log.d(TAG, "TouchEvent:" + e.getAction() + " - " + tx + "/" + UiBridge.metrics.center.x);
        if (tx < UiBridge.metrics.center.x) {
            // jump
            if (!jumping) {
                Log.d(TAG, "Jumping");
                jumping = true;
                speed = JUMP_POWER;
                setAnimState(AnimState.jump);
            }
        } else {
            // slide
        }
        return false;
    }
}
