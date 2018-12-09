package bishodroid.com.gymapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

import bishodroid.com.gymapp.R;
import bishodroid.com.gymapp.adapter.WorkoutStatusAdapter;
import bishodroid.com.gymapp.db.DBAccess;
import bishodroid.com.gymapp.model.Workout;
import bishodroid.com.gymapp.util.AppSettings;

public class MyWorkouts extends AppCompatActivity {

    private List<Workout> workouts;
    private WorkoutStatusAdapter adapter;
    private DBAccess db;
    private AppSettings settings;

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workouts);

        db = DBAccess.getInstance(getApplicationContext());
        db.open();
        settings = AppSettings.getInstance(getApplicationContext());

        workouts = db.getWorkouts(settings.getId());
        adapter = new WorkoutStatusAdapter(this,workouts);

        list = findViewById(R.id.myworkouts);
        list.setAdapter(adapter);
        db.close();

    }
}
