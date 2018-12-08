package bishodroid.com.gymapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bishodroid.com.gymapp.R;
import bishodroid.com.gymapp.model.Meal;

public class MealsAdapter extends BaseAdapter {

    private Context context;
    private List<Meal> meals;

    public MealsAdapter(Context context, List<Meal> meals) {
        this.context = context;
        this.meals = meals;
    }

    @Override
    public int getCount() {
        return meals.size();
    }

    @Override
    public Object getItem(int position) {
        return meals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final Meal meal = meals.get(position);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.meal_list_adapter, null);
            ImageView img = convertView.findViewById(R.id.meal_img);
            Glide.with(context)
            .load(meal.getImgUrl())
            .into(img);

            TextView name = convertView.findViewById(R.id.meal_name);
            name.setText(meal.getName());
            TextView cals = convertView.findViewById(R.id.meal_cals);
            cals.setText(String.valueOf(meal.getCalories())+" calories");


        }
        return convertView;
    }
}
