package com.rockooapps.carrentals;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashScreen extends Activity {

    ImageView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        view = (ImageView)findViewById(R.id.LogoImage);
        view.setImageResource(R.drawable.carrents);

        Thread sa = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                    Intent sp = new  Intent(SplashScreen.this,Login_User.class);
                    startActivity(sp);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }
        };
        sa.start();
    }
}
