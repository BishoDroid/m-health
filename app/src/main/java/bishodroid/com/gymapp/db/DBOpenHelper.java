package bishodroid.com.gymapp.db;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class DBOpenHelper extends SQLiteAssetHelper {

    private static String DB_PATH = "/data/data/bishodroid.com.gymapp/databases/";
    private static String DB_NAME = "m-health.db";
    private SQLiteDatabase db;
    private Context context;

    DBOpenHelper(Context context){
        super(context, DB_NAME, null,1);
        if(context == null){
            try{
                createDatabase();
                openDatabase();
            }catch (IOException e){
                Log.e("OPEN_HELPER",e.getMessage());
            }catch (SQLException e){
                Log.e("OPEN_HELPER", e.getMessage());
            }
        }
    }

    private void createDatabase() throws IOException {
        boolean dbExists = false;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READONLY);
        if (db != null) {
            dbExists = true;
            db.close();
        }

        if (dbExists) {
            Log.d("DBOpenHelper", "DB already exists.");
        } else {
            this.getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void openDatabase() throws SQLException {
        String path = DB_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    private void copyDatabase() throws IOException {
        InputStream ip = context.getAssets().open(DB_NAME);
        String dbFile = DB_PATH + DB_NAME;
        OutputStream os = new FileOutputStream(dbFile);

        byte[] buffer = new byte[2048];
        int length;
        while ((length = ip.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        os.flush();
        os.close();
        ip.close();
    }
}
