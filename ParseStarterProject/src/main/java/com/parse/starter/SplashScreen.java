package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Nilesh Verma on 3/7/2016.
 */
public class SplashScreen extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        startMainActivity();
    }

    private void startMainActivity() {

        Thread hit = new Thread() {
            public void run() {
                try {
                    sleep(1800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent OpenMainActivity = new Intent(SplashScreen.this,UserList.class);
                    startActivity(OpenMainActivity);
                }
            }

        };
        hit.start();

    }
}
