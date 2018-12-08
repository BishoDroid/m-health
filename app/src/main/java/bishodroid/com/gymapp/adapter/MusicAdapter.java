package bishodroid.com.gymapp.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import bishodroid.com.gymapp.R;
import bishodroid.com.gymapp.model.Song;

public class MusicAdapter extends BaseAdapter {

    private MediaPlayer player;
    private Context context;
    private List<Song> songs;

    public MusicAdapter(Context context, List<Song> songs){
        this.context = context;
        this.songs = songs;
        player = new MediaPlayer();
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int position) {
        return songs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final Song song = songs.get(position);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.music_adapter, null);
            TextView title = convertView.findViewById(R.id.song_title);
            title.setText(song.getTitle());
            final ImageView img = convertView.findViewById(R.id.song_play_stop);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(player.isPlaying()){
                        player.stop();
                        player.reset();
                        img.setImageResource(R.drawable.ic_play_arrow_white_24dp);
                    }else{
                        String path = song.getPath();
                        Log.i("MUSIC", "PLAYING..."+path);
                        try {
                            player.setDataSource(path);
                            player.setLooping(true);
                            player.prepare();
                            player.start();
                            img.setImageResource(R.drawable.ic_pause_white_24dp);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });

        }

        return convertView;
    }
}
