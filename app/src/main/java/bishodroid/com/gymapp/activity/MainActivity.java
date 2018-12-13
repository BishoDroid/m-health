package bishodroid.com.gymapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import bishodroid.com.gymapp.R;
import bishodroid.com.gymapp.db.DBAccess;
import bishodroid.com.gymapp.model.User;
import bishodroid.com.gymapp.util.AppSettings;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView profile, workouts, meals, calendar, music, cardio;
    private TextView welcome;
    private User user;
    private AppSettings settings;
    private DBAccess db;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = AppSettings.getInstance(getApplicationContext());
        db = DBAccess.getInstance(getApplicationContext());
        db.open();
        user = db.getUserDetails(settings.getId());
        db.close();


        welcome = findViewById(R.id.textView2);
        profile = findViewById(R.id.main_profile);
        profile.setOnClickListener(this);
        workouts = findViewById(R.id.workouts_chest);
        workouts.setOnClickListener(this);
        meals = findViewById(R.id.main_meals);
        meals.setOnClickListener(this);
        calendar = findViewById(R.id.main_calendar);
        calendar.setOnClickListener(this);
        music = findViewById(R.id.main_music);
        music.setOnClickListener(this);
        cardio = findViewById(R.id.main_cardio);
        cardio.setOnClickListener(this);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(this);

        welcome.setText("Welcome to M-Health, "+user.getName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_profile:
                Intent prof = new Intent(MainActivity.this, Profile.class);
                prof.putExtra("user",user);
                startActivity(prof);
                break;
            case R.id.workouts_chest:
                Intent work = new Intent(MainActivity.this, Workouts.class);
                work.putExtra("userId",user.getId());
                startActivity(work);

                break;
            case R.id.main_meals:
                Intent meals = new Intent(MainActivity.this, Meals.class );
                meals.putExtra("userId", user.getId());
                startActivity(meals);
                break;
            case R.id.main_calendar:
                Intent calendar = new Intent(MainActivity.this, Calendar.class);
                calendar.putExtra("userId", user.getId());
                startActivity(calendar);
                break;
            case R.id.main_music:
                startActivity(new Intent(MainActivity.this, Music.class));
                break;
            case R.id.main_cardio:
                Intent cardio = new Intent(MainActivity.this, Cardio.class);
                cardio.putExtra("userId", user.getId());
                startActivity(cardio);
                break;
            case R.id.logout:
                settings.setId(-1);
                startActivity(new Intent(MainActivity.this, Login.class));
                    break;
                default:
                    Log.d("MAIN", "Not registered for clickListener");
        }
    }
}
