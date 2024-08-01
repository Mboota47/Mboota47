package com.example.easynotes.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import com.example.easynotes.MainActivity;
import com.example.easynotes.R;

public class SplashAtivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_ativity);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashAtivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}