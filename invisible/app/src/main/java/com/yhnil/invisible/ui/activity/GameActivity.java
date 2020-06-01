package com.yhnil.invisible.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.yhnil.invisible.framework.main.UiBridge;
import com.yhnil.invisible.framework.view.GameView;
import com.yhnil.invisible.game.scene.FirstScene;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UiBridge.setActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));

        new FirstScene().run();
    }
}
