package bishodroid.com.gymapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bishodroid.com.gymapp.R;
import bishodroid.com.gymapp.adapter.WorkoutListAdapter;
import bishodroid.com.gymapp.model.Workout;

public class Cardio extends AppCompatActivity {

    private List<Workout> cardios;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio);

        //TODO get cardios from db based on category
        cardios = new ArrayList<>();

        String[] cardioNames = getResources().getStringArray(R.array.cardio_names);
        String[] cardioValues = getResources().getStringArray(R.array.cardio_values);
        for(int i=0; i< cardioNames.length; i++){
            cardios.add(new Workout(cardioNames[i], cardioValues[i], "cardio"));
        }

        list = findViewById(R.id.cardio_list);
        WorkoutListAdapter adapter = new WorkoutListAdapter(this, cardios);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO start youtube video
                Toast.makeText(Cardio.this,cardios.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
