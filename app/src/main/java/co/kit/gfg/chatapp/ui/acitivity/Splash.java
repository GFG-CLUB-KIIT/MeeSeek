package co.kit.gfg.chatapp.ui.acitivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

import co.kit.gfg.chatapp.R;
import co.kit.gfg.chatapp.UserInfo;

public class Splash extends AppCompatActivity {
    LottieAnimationView animationView;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        animationView = findViewById(R.id.animation);
        layout = findViewById(R.id.splash_background);

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    // Splash screen delay time
                    layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            animationView.setVisibility(View.VISIBLE);
                            animationView.playAnimation();
                        }
                    });
                    sleep(2000);
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