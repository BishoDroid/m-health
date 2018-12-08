package bishodroid.com.gymapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bishodroid.com.gymapp.R;
import bishodroid.com.gymapp.db.DBAccess;
import bishodroid.com.gymapp.model.User;
import bishodroid.com.gymapp.model.Workout;
import bishodroid.com.gymapp.util.AppSettings;
import bishodroid.com.gymapp.util.Constants;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    private static final String CM = " CM";
    private static final String KG = " Kg";
    private static final String YRS = " Yrs";

    private static boolean editMode = false;

    private ImageView image;
    private EditText email, password, age, weight, height, gender;
    private ProgressBar progress;
    private TextView percent;
    private Button editSave;
    private User user;
    private List<Workout> workouts;

    private DBAccess db;
    private AppSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        db = DBAccess.getInstance(getApplicationContext());
        settings = AppSettings.getInstance(getApplicationContext());

        db.open();
        user = db.getUserDetails(settings.getId());
        workouts = db.getWorkoutsWithDate(null, user.getId());
        int doneWorkouts = getDoneWorkouts(workouts);
        db.close();

        image = findViewById(R.id.profile_image);
        image.setOnClickListener(this);
        email = findViewById(R.id.profile_email);
        email.setText(user.getEmail());
        password = findViewById(R.id.profile_password);
        password.setText(user.getPassword());

        age = findViewById(R.id.profile_age);
        age.setText(String.valueOf(user.getAge() + YRS));

        weight = findViewById(R.id.profile_weight);
        weight.setText(String.valueOf(user.getWeight() + KG));

        height = findViewById(R.id.profile_height);
        height.setText(String.valueOf(user.getHeight() + CM));

        gender = findViewById(R.id.profile_gender);
        gender.setText(user.getGender());

        progress = findViewById(R.id.profile_workout_progress);
        progress.setProgress(0);
        percent = findViewById(R.id.profile_prog_percent);

        int done = workouts.size() > 0 ? (doneWorkouts / workouts.size()) * 100 : 0;
        percent.setText(done+"% of your daily workout");

        editSave = findViewById(R.id.profile_edit_save);
        editSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_image:
                addImage();
                break;
            case R.id.profile_edit_save:
                editOrSave();
        }
    }


    private void addImage() {

    }

    private int getDoneWorkouts(List<Workout> workouts){
        int done = 0;
        for(Workout w : workouts){
            if(w.isDone()) done++;
        }
        return done;
    }
    private void editOrSave() {
        editMode = !editMode;
        performAction(editMode);
        updateFields(editMode);
    }

    private void updateFields(boolean isEditMode) {
        if (isEditMode) {
            editSave.setText("Save");
            email.setEnabled(true);
            password.setEnabled(true);
            password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            age.setEnabled(true);
            height.setEnabled(true);
            weight.setEnabled(true);
            gender.setEnabled(true);
        } else {
            editSave.setText("Edit");
            email.setEnabled(false);
            password.setEnabled(false);
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            age.setEnabled(false);
            height.setEnabled(false);
            weight.setEnabled(false);
            gender.setEnabled(false);
        }
    }

    private void performAction(boolean isEditMode) {
        if (!isEditMode) {
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());
            user.setAge(Integer.valueOf(age.getText().toString().split(" ")[0]));
            user.setHeight(Integer.valueOf(height.getText().toString().split(" ")[0]));
            user.setWeight(Integer.valueOf(weight.getText().toString().split(" ")[0]));
            user.setGender(gender.getText().toString());

            db.open();
            db.updateUser(user);
            db.close();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }
    }

}
