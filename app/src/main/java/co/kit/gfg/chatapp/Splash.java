package co.kit.gfg.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    // Splash screen delay time
                    sleep(1500);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {


                    boolean isLoggedIn= UserInfo.isLoggedIn(Splash.this);
                    Intent intent;

                    /*If user has already Logged in */
                    if(isLoggedIn){
                        intent = new Intent(Splash.this, MainActivity.class);

                    }
                    else {

                        intent = new Intent(Splash.this, LoginActivity.class);
                    }
                    startActivity(intent);
                    finish();
                }
            }
        }; thread.start();
    }
}