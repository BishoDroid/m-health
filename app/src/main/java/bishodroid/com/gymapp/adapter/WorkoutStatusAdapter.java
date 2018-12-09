package bishodroid.com.gymapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flipkart.youtubeview.activity.YouTubeActivity;

import java.util.List;

import bishodroid.com.gymapp.R;
import bishodroid.com.gymapp.db.DBAccess;
import bishodroid.com.gymapp.model.Workout;
import bishodroid.com.gymapp.util.Constants;

public class WorkoutStatusAdapter extends BaseAdapter{

    List<Workout> workouts;
    Context context;
    TextView title;
    ImageView status;
    DBAccess db;
    boolean isDone;

    public WorkoutStatusAdapter(Context context, List<Workout> workouts){
        this.context = context;
        this.workouts = workouts;
        db = DBAccess.getInstance(context.getApplicationContext());
    }
    @Override
    public int getCount() {
        return workouts.size();
    }

    @Override
    public Object getItem(int position) {
        return workouts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return workouts.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final Workout workout = workouts.get(position);
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.workout_status, null);
            title = convertView.findViewById(R.id.status_title);
            title.setText(workout.getTitle());
            status = convertView.findViewById(R.id.status_update);

            status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent video = new Intent(context, YouTubeActivity.class);
                    video.putExtra("apiKey", Constants.YOUTUBE_API_KEY);
                    video.putExtra("videoId",workout.getVideoUrl());
                    context.startActivity(video);
                }
            });

        }
        return convertView;
    }
}
