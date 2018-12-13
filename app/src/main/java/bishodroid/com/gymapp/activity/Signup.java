package bishodroid.com.gymapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import bishodroid.com.gymapp.R;
import bishodroid.com.gymapp.db.DBAccess;
import bishodroid.com.gymapp.model.User;

public class Signup extends AppCompatActivity implements View.OnClickListener{

    private TextInputEditText name, email, password, age, height, weight;
    private RadioGroup gender;
    private Button signup, login;
    private DBAccess db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.signup_name);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        age = findViewById(R.id.signup_age);
        height = findViewById(R.id.signup_height);
        weight = findViewById(R.id.signup_weight);

        gender = findViewById(R.id.signup_gender);
        signup = findViewById(R.id.signup_signup);
        signup.setOnClickListener(this);
        login = findViewById(R.id.signup_login);
        login.setOnClickListener(this);

        db = DBAccess.getInstance(getApplicationContext());
        db.open();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signup_signup:
                signup();
                break;
            case R.id.signup_login:

                //Finish this activity and go to Login when user taps on Login
                finish();
                startActivity(new Intent(Signup.this, Login.class));

                break;
        }
    }

    /**
     * This method will perform the user signup and will save their information in database.
     */
    private void signup(){
        User user = createUser();
        String result = db.registerUser(user);
        if(result == "OK"){
            Toast.makeText(this, "Successfully registered: "+user.getName(), Toast.LENGTH_LONG).show();
            User u = db.getUserDetails(user.getEmail(), user.getPassword());
            db.close();

            Intent login = new Intent(this, Login.class);
            login.putExtra("user", u);
            startActivity(login);
            finish();
        }
    }

    private User createUser(){
        User user = new User();
        user.setName(name.getText().toString());
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
        RadioButton btn = findViewById(gender.getCheckedRadioButtonId());
        user.setGender(btn.getText().toString());
        user.setAge(Integer.valueOf(age.getText().toString()));
        user.setHeight(Integer.valueOf(height.getText().toString()));
        user.setWeight(Integer.valueOf(weight.getText().toString()));
        user.setGoal("gain");

        return user;
    }
}
