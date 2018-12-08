package bishodroid.com.gymapp.activity;

import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
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
