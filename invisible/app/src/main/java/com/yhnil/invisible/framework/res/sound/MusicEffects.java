package com.yhnil.invisible.framework.res.sound;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.yhnil.invisible.R;

import java.util.HashMap;

public class MusicEffects {
    private static final String TAG = MusicEffects.class.getSimpleName();
    private static MusicEffects singleton;
    private MediaPlayer mediaPlayer;

    public static MusicEffects get() {
        if (singleton == null) {
            singleton = new MusicEffects();
        }
        return singleton;
    }
    public MusicEffects() {
          this.mediaPlayer = new MediaPlayer();
    }
    public void load(Context context , int resId) {
            mediaPlayer = MediaPlayer.create(context , resId);
    }
}
