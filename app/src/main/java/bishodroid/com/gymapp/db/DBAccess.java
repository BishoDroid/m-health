package bishodroid.com.gymapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import bishodroid.com.gymapp.model.Meal;
import bishodroid.com.gymapp.model.User;
import bishodroid.com.gymapp.model.Workout;

public class DBAccess {

    private static final String GET_BY_ID = "select * from users where id = ?";
    private static final String GET_BY_EMAIL_PASS = "select * from users where email = ? and password = ?";
    private static final String GET_WORKOUT_WITH_DATE = "select * from workouts where date = ? and user_id = ?";
    private static final String GET_WORKOUT_FOR_USER = "select * from workouts where user_id = ?";
    private static final String GET_MEALS = "select * from meals order by calories";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DBAccess instance;

    private DBAccess(Context context){
        this.openHelper = new DBOpenHelper(context);
    }

    /**
     * Single Instance creator
     * @param context
     * @return
     */
    public static DBAccess getInstance(Context context){
        if(instance == null){
            instance = new DBAccess(context);
        }
        return instance;
    }

    /**
     * Opens database for writing
     */
    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    /**
     * Closes the database
     */
    public void close(){
        if(db != null){
            this.db.close();
        }
    }


    /**
     * Register a new user
     * @param user
     * @return
     */
    public String registerUser(User user){
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        values.put("gender", user.getGender());
        values.put("goal", user.getGoal());
        values.put("age", user.getAge());
        values.put("height", user.getHeight());
        values.put("weight", user.getWeight());

        long res = db.insert("users",null, values);
        return res >= 1 ? "OK" : "NOT_OK";
    }

    /**
     * Get user by email and password
     * @param email
     * @param password
     * @return User
     */
    public User getUserDetails(String email, String password){
        String[] data = {email, password};

        Cursor c = db.rawQuery(GET_BY_EMAIL_PASS,data);
        c.moveToFirst();
        User user = null;
        while(!c.isAfterLast()) {
            user  = new User();
            user.setId(c.getInt(0));
            user.setName(c.getString(1));
            user.setEmail(c.getString(2));
            user.setPassword(c.getString(3));
            user.setGender(c.getString(4));
            user.setHeight(c.getInt(5));
            user.setAge(c.getInt(6));
            user.setWeight(c.getInt(7));
            user.setGoal(c.getString(8));
            c.moveToNext();
        }
        c.close();

        if(user == null){
            return null;
        }

        return user;
    }


    /**
     * Gets all the meals stored in the DB ordered by number of calories
     * @return List of Meal
     */
    public List<Meal> getMeals(){
        List<Meal> meals = new ArrayList<>();
        Cursor c = db.rawQuery(GET_MEALS, null);
        c.moveToFirst();
        Meal meal = null;
        while(c.moveToNext()){
            meal = new Meal();
            meal.setName(c.getString(1));
            meal.setRecipe(c.getString(2));
            meal.setSteps(c.getString(3));
            meal.setCalories(c.getInt(4));
            meal.setProtein(c.getInt(5));
            meal.setCarbs(c.getInt(6));
            meal.setImgUrl(c.getString(7));
            meals.add(meal);
        }
        c.close();
        return meals;
    }

    /**
     * Get the user by id
     * @param id
     * @return User
     */
    public User getUserDetails(long id){
        String[] data = {String.valueOf(id)};

        Cursor c = db.rawQuery(GET_BY_ID,data);
        c.moveToFirst();
        User user = null;
        while(!c.isAfterLast()) {
            user  = new User();
            user.setId(c.getInt(0));
            user.setName(c.getString(1));
            user.setEmail(c.getString(2));
            user.setPassword(c.getString(3));
            user.setGender(c.getString(4));
            user.setHeight(c.getInt(5));
            user.setAge(c.getInt(6));
            user.setWeight(c.getInt(7));
            user.setGoal(c.getString(8));
            c.moveToNext();
        }
        c.close();

        if(user == null){
            return null;
        }

        return user;
    }


    /**
     * Update the user
     * @param user
     * @return OK if updated, else NOT_OK
     */
    public String updateUser(User user){
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        values.put("gender", user.getGender());
        values.put("age", user.getAge());
        values.put("height", user.getHeight());
        values.put("weight", user.getWeight());

        long res = db.update("users",values,"id="+user.getId(),null);

        return res >= 1 ? "OK" : "NOT_OK";
    }

    public String markWorkoutAsDone(Workout workout){
        ContentValues values = new ContentValues();
        values.put("done", String.valueOf(workout.isDone()));
        long res = db.update("workouts", values, "userId="+workout.getUserId(), null);
        return res >= 1 ? "OK" : "NOT_OK";
    }

    public List<Workout> getWorkoutsWithDate(String date, long userId){
        List<Workout> list = new ArrayList<>();
        String[] data = date != null ? new String[]{date, String.valueOf(userId)} : null;
        Cursor c = db.rawQuery(date != null ? GET_WORKOUT_WITH_DATE : GET_WORKOUT_FOR_USER, data);
        c.moveToFirst();
        Workout workout;
        while(c.moveToNext()){
            Log.d("DB", c.toString());
            workout = new Workout();

            workout.setTitle(c.getString(1));
            workout.setCategory(c.getString(2));
            workout.setVideoUrl(c.getString(3));
            workout.setDate(c.getString(4));
            workout.setDone("true".equalsIgnoreCase(c.getString(5)));
            list.add(workout);
        }
        return list;
    }


    public String addWorkout(Workout workout){
        ContentValues values = new ContentValues();
        values.put("title", workout.getTitle());
        values.put("category",workout.getCategory());
        values.put("video", workout.getVideoUrl());
        values.put("date", workout.getDate());
        values.put("done",workout.isDone());
        values.put("user_id",workout.getUserId());
        long res = db.insert("workouts",null, values);
        return res >= 1 ? "OK" : "NOT_OK";
    }
}
