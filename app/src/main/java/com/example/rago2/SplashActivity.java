package com.example.rago2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    TextView rago;
    TextView textViews2;
    Animation topAnimation,middleAnimation,bottomanimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanimation=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        middleAnimation =AnimationUtils.loadAnimation(this,R.anim.middle_animation);

        rago=findViewById(R.id.textView2);
        textViews2=findViewById(R.id.tv2);


        rago.setAnimation(topAnimation);
        textViews2.setAnimation(middleAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
        },8000);
    }
}