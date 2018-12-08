package bishodroid.com.gymapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import bishodroid.com.gymapp.R;
import bishodroid.com.gymapp.model.Meal;

public class MealDetails extends AppCompatActivity {

    private ImageView img;
    private TextView name, calories, protein, carbs, ingredients, steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        Meal meal  = (Meal)getIntent().getSerializableExtra("meal");

        name = findViewById(R.id.mealdetail_name);
        name.setText(meal.getName());

        img = findViewById(R.id.mealdetail_img);
        Glide.with(this).load(meal.getImgUrl()).into(img);

        calories = findViewById(R.id.mealdetail_calories);
        calories.setText(String.valueOf(meal.getCalories()) +" calories");

        protein = findViewById(R.id.mealdetail_protein);
        protein.setText(String.valueOf(meal.getProtein()) + " protein");

        carbs = findViewById(R.id.mealdetail_carbs);
        carbs.setText(String.valueOf(meal.getCarbs()) + " carbs");

        ingredients = findViewById(R.id.mealdetail_ingredients);
        ingredients.setText(meal.getRecipe());

        steps = findViewById(R.id.mealdetail_steps);
        steps.setText(meal.getSteps());

    }
}
