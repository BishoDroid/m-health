package bishodroid.com.gymapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import bishodroid.com.gymapp.R;

public class Workouts extends AppCompatActivity implements View.OnClickListener{

    private Button track;
    private ImageView chest, back, shoulders, sixpack, legs, arms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        track = findViewById(R.id.workouts_view_current);
        track.setOnClickListener(this);

        chest = findViewById(R.id.workouts_chest);
        chest.setOnClickListener(this);
        back = findViewById(R.id.workouts_back);
        back.setOnClickListener(this);
        shoulders = findViewById(R.id.workouts_shoulders);
        shoulders.setOnClickListener(this);
        sixpack = findViewById(R.id.workouts_sixpack);
        sixpack.setOnClickListener(this);
        legs = findViewById(R.id.workouts_legs);
        legs.setOnClickListener(this);
        arms = findViewById(R.id.workouts_arms);
        arms.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(Workouts.this, WorkoutList.class);

        switch (v.getId()){
            case R.id.workouts_chest:
                i.putExtra("workout", "chest");
                startActivity(i);
                break;
            case R.id.workouts_back:
                i.putExtra("workout", "back");
                startActivity(i);
                break;
            case R.id.workouts_shoulders:
                i.putExtra("workout", "shoulders");
                startActivity(i);
                break;
            case R.id.workouts_sixpack:
                i.putExtra("workout", "abs");
                startActivity(i);
                break;
            case R.id.workouts_legs:
                i.putExtra("workout", "legs");
                startActivity(i);
                break;
            case R.id.workouts_arms:
                i.putExtra("workout", "arms");
                startActivity(i);
                break;
            case R.id.workouts_view_current:
                //TODO show progress in dialog
                break;
                default:
                    Log.i("WORKOUTS", "Unknown choice, setting default: chest");
        }
    }
}
