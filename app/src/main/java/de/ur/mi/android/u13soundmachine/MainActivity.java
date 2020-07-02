package de.ur.mi.android.u13soundmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.io.IOException;

import de.ur.mi.android.u13soundmachine.config.AppConfig;
import de.ur.mi.android.u13soundmachine.helper.MediaPlayerHelper;

public class MainActivity extends AppCompatActivity {

    GridLayout gridLayout;
    MediaPlayerHelper mediaPlayerHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayerHelper = new MediaPlayerHelper(this);
        initUI();
    }

    private void initUI() {
        gridLayout = findViewById(R.id.grid_sounds);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            final int resId = AppConfig.SOUND_IDS[i];
            Button soundButton = (Button) gridLayout.getChildAt(i);
            soundButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playAudio(resId);
                }
            });
        }
    }

    private void playAudio(int resId) {
        try {
            mediaPlayerHelper.playAudio(resId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
