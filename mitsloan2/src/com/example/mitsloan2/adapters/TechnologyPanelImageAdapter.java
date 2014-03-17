package com.example.mitsloan2.adapters;

import com.example.mitsloan2.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class TechnologyPanelImageAdapter extends BaseAdapter{
	private Context mContext;

	// Keep all Images in array
    public Integer[] mThumbIds = { R.drawable.anne_githuku_songwe, 
    		R.drawable.ayodeji_adewunmi, 
    		R.drawable.dr_bitange_ndemo,
    		R.drawable.joost_bonsen,
    		R.drawable.sangu_delle};
    
	// Constructor
    public TechnologyPanelImageAdapter(Context c){
        mContext = c;
    }
    
	@Override
	public int getCount() {
		return mThumbIds.length;
	}

	@Override
	public Object getItem(int position) {
		return mThumbIds[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
        return imageView;
	}

}
