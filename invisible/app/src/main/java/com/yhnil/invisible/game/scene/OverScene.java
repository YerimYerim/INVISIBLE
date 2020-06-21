package com.yhnil.invisible.game.scene;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.RectF;
import android.media.MediaPlayer;

import com.yhnil.invisible.R;
import com.yhnil.invisible.framework.main.GameTimer;
import com.yhnil.invisible.framework.main.UiBridge;
import com.yhnil.invisible.framework.obj.BitmapObject;
import com.yhnil.invisible.framework.obj.ScoreObject;
import com.yhnil.invisible.framework.obj.ui.Button;
import com.yhnil.invisible.framework.res.sound.SoundEffects;
import com.yhnil.invisible.framework.view.GameView;

public class OverScene extends com.yhnil.invisible.framework.main.GameScene {
    private static final String TAG = OverScene.class.getSimpleName();
    public ScoreObject scoreObject;
    public ScoreObject bestScore;
    private int soundPosition;

    public enum Layer {
        bg, enemy, player, ui, COUNT
    }
    private GameTimer timer;
    private MediaPlayer mediaPlayer;
    @Override
    protected int getLayerCount() {
        return Layer.COUNT.ordinal();
    }

    @Override
    public void update() {
        super.update();
        if (timer.done()) {
            timer.reset();
        }
    }

    @Override
    public void exit() {
        mediaPlayer.release();
        super.exit();
    }

    @Override
    public void onPause() {
        mediaPlayer.pause();
        soundPosition = mediaPlayer.getCurrentPosition();
        super.onPause();
    }

    @Override
    public void onResume() {
        mediaPlayer.seekTo(soundPosition);
        mediaPlayer.start();
        super.onResume();
    }

    @Override
    public void enter() {
        super.enter();
        initObjects();

        Context context = UiBridge.getView().getContext();
        SharedPreferences prefs = context.getSharedPreferences("Highscore", Context.MODE_PRIVATE);
        int score = prefs.getInt("score", 0);
        bestScore.reset();
        bestScore.add(score);

        if(bestScore.getScoreValue() < scoreObject.getScoreValue()) {
            SharedPreferences.Editor editor = prefs.edit();

            editor.putInt("score", scoreObject.getScoreValue());
            editor.commit();
        }
    }

    private void initObjects() {
        timer = new GameTimer(2, 1);
        mediaPlayer = GameView.soundMusic.play(R.raw.ending);
        mediaPlayer.setLooping(false);
        mediaPlayer.start();

        timer = new GameTimer(2, 1);

        int cx = UiBridge.metrics.center.x;
        int cy = UiBridge.metrics.center.y;
        BitmapObject score_image = new BitmapObject(cx , cy - UiBridge.y(30), UiBridge.x(300), UiBridge.y(300), R.mipmap.score_image_);

        RectF bestScoreBox = new RectF(cx, cy- UiBridge.y(195), cx+UiBridge.x(32),cy- UiBridge.y(145));
        bestScore = new ScoreObject(R.mipmap.number, bestScoreBox);
        BitmapObject bestScoreImage = new BitmapObject(cx , cy - UiBridge.y(170), UiBridge.x(200),UiBridge.y(200),R.mipmap.bestscore);
        gameWorld.add(Layer.ui.ordinal(), bestScoreImage);
        gameWorld.add(Layer.ui.ordinal(), score_image);

        gameWorld.add(Layer.ui.ordinal(), bestScore);
        gameWorld.add(Layer.ui.ordinal(), scoreObject);

        Button button = new Button(cx + UiBridge.x(50), cy+UiBridge.y(200), 200,200, R.mipmap.restart_, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        Button Menu = new Button(cx - UiBridge.x(50), cy+UiBridge.y(200),200,200, R.mipmap.menu, R.mipmap.blue_round_btn, R.mipmap.red_round_btn);
        button.setOnClickRunnable(new Runnable() {

            @Override
            public void run() {
               GameScene gameScene = new GameScene();
               pop();
               SoundEffects.get().play(R.raw.stoneeat);
               gameScene.push();
            }
        });
        Menu.setOnClickRunnable(new Runnable() {
            @Override
            public void run() {
                pop();
                MenuScene menuScene = new MenuScene();
                SoundEffects.get().play(R.raw.stoneeat);
                menuScene.push();

            }
        });
        gameWorld.add(Layer.ui.ordinal(), Menu);
        gameWorld.add(Layer.ui.ordinal(), button);
    }

    public void getScore(ScoreObject Scoreobject) {
        scoreObject = Scoreobject;
        int cx = UiBridge.metrics.center.x;
        int cy = UiBridge.metrics.center.y;
        RectF rbox = new RectF(cx, cy - UiBridge.y(25), cx+UiBridge.x(32),cy+ UiBridge.y(25));
        scoreObject.setRect(rbox);
    }
}
