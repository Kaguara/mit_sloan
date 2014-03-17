package com.example.mitsloan2.adapters;

import com.example.mitsloan2.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class EnergyPanelImageAdapter extends BaseAdapter{
	private Context mContext;

	// Keep all Images in array
    public Integer[] mThumbIds = { R.drawable.alex_bashian, 
    		R.drawable.bamidele_omotowa, 
    		R.drawable.evan_pressman_ergo,
    		R.drawable.joseph_simbakalia,
    		R.drawable.mamadou_toure,
    		R.drawable.robert_stoner};
    
	// Constructor
    public EnergyPanelImageAdapter(Context c){
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
