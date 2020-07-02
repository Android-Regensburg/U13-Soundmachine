package de.ur.mi.android.u13soundmachine.helper;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;
import java.util.ArrayList;

import de.ur.mi.android.u13soundmachine.config.AppConfig;

public class MediaPlayerHelper implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {

    private Context context;
    private ArrayList<MediaPlayer> playerPool;
    private ArrayList<MediaPlayer> usedPlayers;

    public MediaPlayerHelper(Context context) {
        this.context = context;
        playerPool = new ArrayList<>();
        for (int i = 0; i < AppConfig.MAX_MEDIA_STREAMS; i++) {
            playerPool.add(buildPlayer());
        }
        usedPlayers = new ArrayList<>();
    }

    private MediaPlayer buildPlayer() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
        return mediaPlayer;
    }

    private MediaPlayer requestPlayer() {
        if (!playerPool.isEmpty()) {
            MediaPlayer mediaPlayer = playerPool.remove(0);
            usedPlayers.add(mediaPlayer);
            return mediaPlayer;
        }
        return null;
    }

    private void recyclePlayer(MediaPlayer mediaPlayer) {
        mediaPlayer.reset();
        usedPlayers.remove(mediaPlayer);
        playerPool.add(mediaPlayer);
    }

    public void playAudio(int resId) throws IOException {
        AssetFileDescriptor assetFileDescriptor = context.getResources().openRawResourceFd(resId);
        if (assetFileDescriptor == null) return;
        MediaPlayer mediaPlayer = requestPlayer();
        if (mediaPlayer == null) return;
        mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getDeclaredLength());
        mediaPlayer.prepareAsync();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        recyclePlayer(mp);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }
}
