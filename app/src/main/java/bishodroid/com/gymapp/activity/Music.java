package bishodroid.com.gymapp.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import bishodroid.com.gymapp.R;
import bishodroid.com.gymapp.adapter.MusicAdapter;
import bishodroid.com.gymapp.model.Song;

public class Music extends AppCompatActivity {

    private String[] proj = {"*"};
    private List<Song> songs;
    private ListView list;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        //Check if the app has permission to read storage
         if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                 PackageManager.PERMISSION_GRANTED) {

                 AlertDialog.Builder dialog= new AlertDialog.Builder(this);
                 dialog.setMessage("You need to grant  the app permission to read your storage");
                 dialog.setTitle("Permission required");
                 dialog.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         dialog.dismiss();
                         ActivityCompat.requestPermissions(Music.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                     }
                 });
                 dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         dialog.dismiss();
                         startActivity(new Intent(Music.this, MainActivity.class));
                     }
                 });
                 dialog.create().show();
         }

        Cursor c = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, proj,
                MediaStore.Audio.Media.IS_MUSIC + " != 0", null, null);

        songs = new ArrayList<>();

        list = findViewById(R.id.music_songs);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    int name = c.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
                    int dur = c.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);
                    String path=c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA));
                    if(!path.endsWith(".mp3")|| path.contains("AUD-")){
                        Log.i("MUSIC","Skipping: "+path);
                    }else{
                        songs.add(new Song(c.getString(name), path));
                    }

                    Log.i("MUSIC", "DURATION: " + dur);
                } while (c.moveToNext());
            }
            c.close();
        }

        MusicAdapter adapter = new MusicAdapter(this,songs);
        list.setAdapter(adapter);
    }
}
