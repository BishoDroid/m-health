package bishodroid.com.gymapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bishodroid.com.gymapp.R;
import bishodroid.com.gymapp.model.Workout;
import bishodroid.com.gymapp.adapter.WorkoutListAdapter;

public class WorkoutList extends AppCompatActivity {

    private List<Workout> workouts;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);
        String workout = getIntent().getStringExtra("workout");

        workouts = new ArrayList<>();
        switch (workout){
            case "chest":
                String[] chestWorkoutsNames = getResources().getStringArray(R.array.chest_workouts_names);
                String[] chestWorkoutsVals = getResources().getStringArray(R.array.chest_workout_values);
                for(int i=0; i < chestWorkoutsNames.length; i++){
                    workouts.add(new Workout(chestWorkoutsNames[i], chestWorkoutsVals[i], "chest"));
                }
                break;
            case "back":
                String[] backWorkoutsNames = getResources().getStringArray(R.array.back_workout_names);
                String[] backWorkoutsVals = getResources().getStringArray(R.array.back_workout_values);
                for(int i=0; i <backWorkoutsNames.length; i++){
                    workouts.add(new Workout(backWorkoutsNames[i], backWorkoutsVals[i], "back"));
                }
                break;
            case "shoulders":
                String[] shouldersWorkoutsNames = getResources().getStringArray(R.array.shoulders_workout_names);
                String[] shouldersWorkoutsVals = getResources().getStringArray(R.array.shoulders_workout_values);
                for(int i=0; i <shouldersWorkoutsNames.length; i++){
                    workouts.add(new Workout(shouldersWorkoutsNames[i], shouldersWorkoutsVals[i], "shoulders"));
                }
                break;
            case "abs":
                String[] absWorkoutsNames = getResources().getStringArray(R.array.abs_workout_names);
                String[] absWorkoutsVals = getResources().getStringArray(R.array.abs_workout_values);
                for(int i=0; i <absWorkoutsNames.length; i++){
                    workouts.add(new Workout(absWorkoutsNames[i], absWorkoutsVals[i], "abs"));
                }
                break;
            case "legs":
                String[] legsWorkoutsNames = getResources().getStringArray(R.array.legs_workout_names);
                String[] legsWorkoutsVals = getResources().getStringArray(R.array.legs_workout_values);
                for(int i=0; i <legsWorkoutsNames.length; i++){
                    workouts.add(new Workout(legsWorkoutsNames[i], legsWorkoutsVals[i], "legs"));
                }
                break;
            case "arms":
                String[] armsWorkoutsNames = getResources().getStringArray(R.array.arms_workout_names);
                String[] armsWorkoutsVals = getResources().getStringArray(R.array.arms_workout_values);
                for(int i=0; i <armsWorkoutsNames.length; i++){
                    workouts.add(new Workout(armsWorkoutsNames[i], armsWorkoutsVals[i], "legs"));
                }
                break;
        }

        list = findViewById(R.id.workouts_list);
        WorkoutListAdapter adapter = new WorkoutListAdapter(this, workouts);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO start youtube video
                Toast.makeText(WorkoutList.this,workouts.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
