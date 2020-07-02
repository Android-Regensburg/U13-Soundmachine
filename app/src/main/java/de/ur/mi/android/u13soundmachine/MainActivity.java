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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
