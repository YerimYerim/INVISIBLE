package com.yhnil.invisible.framework.res.sound;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundMusic {
    private static final String TAG = SoundMusic.class.getSimpleName();
    public MediaPlayer mediaPlayer;
    private SoundMusic soundMusic;
    private Context context;
    public SoundMusic() {
            this.mediaPlayer = new MediaPlayer();
    }
    public void getContext(Context conText){
        context  = conText;
    }
    public MediaPlayer play(int resId) {
        mediaPlayer = MediaPlayer.create(context, resId);
        mediaPlayer.setLooping(true);
        return mediaPlayer;
    }
}
