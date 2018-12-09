package bishodroid.com.gymapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import bishodroid.com.gymapp.R;
import bishodroid.com.gymapp.db.DBAccess;
import bishodroid.com.gymapp.util.AppSettings;

import static java.lang.Thread.sleep;

public class Splash extends AppCompatActivity {

    private AppSettings settings;
    private DBAccess db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        settings = AppSettings.getInstance(getApplicationContext());
        db = DBAccess.getInstance(getApplicationContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if(settings.isFirstRun()){
                        startActivity(new Intent(Splash.this, Login.class));
                        settings.setFirstRun(false);
                        finish();
                    }else{
                        startActivity(new Intent(Splash.this, MainActivity.class));
                        finish();
                    }

                }
            }
        }).start();
    }
}
