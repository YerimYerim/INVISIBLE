package com.yhnil.invisible.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yhnil.invisible.framework.input.sensor.GyroSensor;
import com.yhnil.invisible.framework.main.GameScene;
import com.yhnil.invisible.framework.main.UiBridge;
import com.yhnil.invisible.framework.view.GameView;
import com.yhnil.invisible.game.scene.FirstScene;

public class GameActivity extends AppCompatActivity {

    private static final long BACKKEY_INTERVAL_MSEC = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UiBridge.setActivity(this);
        super.onCreate(savedInstanceState);
       // setContentView(new GameView(this));
        setContentView(new GameView(this));
        new FirstScene().run();
    }

    private long lastBackPressedOn;
    @Override
    public void onBackPressed() {
        long now = System.currentTimeMillis();
        long elapsed = now - lastBackPressedOn;
        if (elapsed <= BACKKEY_INTERVAL_MSEC) {
            handleBackPressed();
            return;
        }
        Log.d("BackKey", "elapsed="+elapsed);
        Toast.makeText(this,
                "Press Back key twice quickly to exit",
                Toast.LENGTH_SHORT)
                .show();
        lastBackPressedOn = now;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (GyroSensor.isCreated()) {
            GyroSensor.get().register();
        }
    }

    @Override
    protected void onPause() {
        if (GyroSensor.isCreated()) {
            GyroSensor.get().unregister();
        }
        super.onPause();
    }

    public void handleBackPressed() {
        GameScene.getTop().onBackPressed();
    }
}
