package com.yhnil.invisible.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
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
        gameView = new GameView(this);
        game.addView(gameView);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        FrameLayout ad = findViewById(R.id.ad);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        ad.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        FrameLayout bAd = findViewById(R.id.bottomAd);
        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        bAd.addView(adView);

        adView.loadAd(adRequest);

        new IntroScene().run();
    }

    private long lastBackPressedOn;
    @Override
    public void onBackPressed() {
        //long now = System.currentTimeMillis();
        //long elapsed = now - lastBackPressedOn;
        //if (elapsed <= BACKKEY_INTERVAL_MSEC) {
        //    handleBackPressed();
        //    return;
        //}
        //Log.d("BackKey", "elapsed="+elapsed);
        //Toast.makeText(this,
        //        "Press Back key twice quickly to exit",
        //        Toast.LENGTH_SHORT)
        //        .show();
        //lastBackPressedOn = now;
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.setPause(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.setPause(true);
    }

    public void handleBackPressed() {
        GameScene.getTop().onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gameView.onDestory();
    }
}
