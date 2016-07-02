package com.satsumasoftware.pokedex.ui.startup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.satsumasoftware.pokedex.R;
import com.satsumasoftware.pokedex.ui.PokedexActivity;
import com.satsumasoftware.pokedex.util.DatabaseUtils;
import com.satsumasoftware.pokedex.util.PrefUtils;

public class SplashActivity extends AppCompatActivity {

    private static final int SLEEP_TIME = 700;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent;
                    if (DatabaseUtils.hasDatabaseUpgraded(getBaseContext())) {
                        intent = new Intent(getBaseContext(), InitializeDbActivity.class);
                    } else if (!PrefUtils.isWelcomeDone(getBaseContext())) {
                        intent = new Intent(getBaseContext(), WelcomeActivity.class);
                    } else {
                        intent = new Intent(getBaseContext(), PokedexActivity.class);
                    }
                    startActivity(intent);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}