package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.ImageView;

public class Home extends AppCompatActivity {
private int splasher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splasher = 2000;
        setContentView(R.layout.activity_home);
        ImageView imahge = findViewById(R.id.imageView);
        imahge.setImageResource(R.drawable.dragon);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Home.this, MainActivity.class);
                Home.this.startActivity(mainIntent);
                Home.this.finish();
            }
        }, splasher);
    }
}
