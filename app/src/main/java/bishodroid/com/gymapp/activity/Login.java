package bishodroid.com.gymapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bishodroid.com.gymapp.R;
import bishodroid.com.gymapp.db.DBAccess;
import bishodroid.com.gymapp.model.User;
import bishodroid.com.gymapp.util.AppSettings;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private EditText email, password;
    private Button login, signup;
    private DBAccess db;
    private AppSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Email and password views initialization
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);

        //Login and signup buttons initialization
        login = findViewById(R.id.login_login);
        login.setOnClickListener(this);
        signup = findViewById(R.id.login_signup);
        signup.setOnClickListener(this);

        settings = AppSettings.getInstance(getApplicationContext());
        db = DBAccess.getInstance(getApplicationContext());
        db.open();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_login:
                login();
                break;
            case R.id.login_signup:

                //Finish this activity and Start the signup activity
                finish();
                startActivity(new Intent(Login.this, Signup.class));

                break;
        }
    }

    /**
     * This method is called when user taps Login button
     * Will take email and password and verify against database
     * If user input is correct, getUserDetails, else show error message
     */
    private void login(){
        User user = db.getUserDetails(email.getText().toString(), password.getText().toString());
        if(user == null){
            Toast.makeText(this, "Wrong user name or password", Toast.LENGTH_LONG).show();
        }else{
            settings.setId(user.getId());
            startActivity(new Intent(Login.this, MainActivity.class));
            db.close();
            finish();
        }

    }
}
