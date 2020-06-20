package com.yhnil.invisible.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.yhnil.invisible.R;
import com.yhnil.invisible.framework.main.GameScene;
import com.yhnil.invisible.framework.main.UiBridge;
import com.yhnil.invisible.framework.view.GameView;
import com.yhnil.invisible.game.scene.IntroScene;

public class GameActivity extends AppCompatActivity {

    private static final long BACKKEY_INTERVAL_MSEC = 1000;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UiBridge.setActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        FrameLayout game = findViewById(R.id.game);
        game.addView(new GameView(this));

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        new IntroScene().run();
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
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void handleBackPressed() {
        GameScene.getTop().onBackPressed();
    }
}
