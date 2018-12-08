package bishodroid.com.gymapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bishodroid.com.gymapp.R;
import bishodroid.com.gymapp.adapter.MealsAdapter;
import bishodroid.com.gymapp.db.DBAccess;
import bishodroid.com.gymapp.model.Meal;

public class Meals extends AppCompatActivity {

    private ListView list;
    private List<Meal> meals;
    private DBAccess db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        db = DBAccess.getInstance(getApplicationContext());
        db.open();
        meals = db.getMeals();
        db.close();

        list = findViewById(R.id.meals_list);
        MealsAdapter adapter = new MealsAdapter(this, meals);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mealDetailsActivity = new Intent(Meals.this, MealDetails.class);
                mealDetailsActivity.putExtra("meal", meals.get(position));
                startActivity(mealDetailsActivity);
                Toast.makeText(Meals.this, meals.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
