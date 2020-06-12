package com.example.ufo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private UFOView ufoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI vervollständigen
        ufoView = new UFOView(this);
        FrameLayout frameLayout = findViewById(R.id.layout1);
        frameLayout.addView(ufoView);
    }
}