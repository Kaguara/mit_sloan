package com.example.mitsloan2.adapters;

import java.util.List;
import java.util.Map;
import com.example.mitsloan2.R;
import com.example.mitsloan2.constants.ScheduleElement;
import com.example.mitsloan2.supportclasses.ImageLoader;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter{
	
	private LayoutInflater inflater;
    private ImageLoader imageLoader;
    private List<Map<String, String>> data;

    public LazyAdapter(Context context, List<Map<String, String>> data) {
        inflater = LayoutInflater.from(context);
        imageLoader = new ImageLoader(context);
        this.data = data;
    }

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_row, null);
        }

        if (convertView != null) {
            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView artist = (TextView) convertView.findViewById(R.id.artist);
            TextView duration = (TextView) convertView.findViewById(R.id.duration);
            ImageView thumbImage = (ImageView) convertView.findViewById(R.id.list_image);

            // Setting all values in ListView
            Map<String, String> song = data.get(position);
            title.setText(song.get(ScheduleElement.TITLE));
            artist.setText(song.get(ScheduleElement.ARTIST));
            duration.setText(song.get(ScheduleElement.DURATION));
            imageLoader.DisplayImage(song.get(ScheduleElement.THUMB_URL), thumbImage);
            this.notifyDataSetChanged(); // Important!
        }

        return convertView;
    }
	
	public void clear() {
        imageLoader.clearCache();
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
	

}
