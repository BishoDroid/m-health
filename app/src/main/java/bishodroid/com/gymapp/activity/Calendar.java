package bishodroid.com.gymapp.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bishodroid.com.gymapp.R;
import bishodroid.com.gymapp.db.DBAccess;
import bishodroid.com.gymapp.model.Workout;
import bishodroid.com.gymapp.util.AppSettings;

public class Calendar extends AppCompatActivity {

    private ListView list;
    private CalendarView calendar;
    private List<Workout> workouts;
    private DBAccess db;
    private AppSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        list = findViewById(R.id.calendar_list);
        calendar = findViewById(R.id.calendar_date);
        db = DBAccess.getInstance(getApplicationContext());
        settings = AppSettings.getInstance(getApplicationContext());


        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                workouts = null;
                String date = dayOfMonth+"-"+month+"-"+year;
                db.open();
                workouts = db.getWorkoutsWithDate(date, settings.getId());

                List<String> strings = new ArrayList<>();
                for(Workout w : workouts){
                    strings.add(w.getTitle());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(Calendar.this, android.R.layout.simple_list_item_1,
                        android.R.id.text1, strings);
                list.setAdapter(adapter);
                TextView text =new TextView(Calendar.this);
                text.setText("No workouts found");
                text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                list.setEmptyView(text);

                db.close();
            }
        });
    }
}
