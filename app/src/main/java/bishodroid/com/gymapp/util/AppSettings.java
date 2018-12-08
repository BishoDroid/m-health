package bishodroid.com.gymapp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSettings {

    private static AppSettings singleton;
    private static Context context;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    /**
     * private {@link AppSettings} constructor
     * @param context - the application context
     */
    private AppSettings(Context context){
        prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor = prefs.edit();
        this.context = context;
    }

    /**
     * Creates a new {@link AppSettings} if doesn't exits,  or returns the current singleton
     * @param context - the application context
     * @return AppSettings
     */
    public static AppSettings getInstance(Context context){
        if(singleton == null) singleton = new AppSettings(context);
        return singleton;
    }

    /**
     * Checks if it is the first run for the app
     * @return
     */
    public boolean isFirstRun(){
        return prefs.getBoolean(Constants.APP_SETTINGS_IS_FIRST_RUN, true);
    }

    public void setFirstRun(boolean value){
        editor.putBoolean(Constants.APP_SETTINGS_IS_FIRST_RUN, value).commit();
    }

    public long getId(){
        return prefs.getLong(Constants.USER_ID, -1L);
    }

    public void setId(long id){
        editor.putLong(Constants.USER_ID, id).commit();
    }
}
